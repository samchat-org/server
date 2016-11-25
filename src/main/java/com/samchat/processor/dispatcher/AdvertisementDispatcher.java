package com.samchat.processor.dispatcher;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDispatch_req;
import com.samchat.common.beans.auto.json.ni.msg.SendBatchMsgFieldBody_req;
import com.samchat.common.beans.auto.json.ni.msg.SendBatchMsg_req;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.db.AdsDbEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.S3Util;
import com.samchat.common.utils.ShardingUtil;
import com.samchat.common.utils.ThreadLocalUtil;
import com.samchat.common.utils.niUtils.NiUtil;
import com.samchat.processor.dispatcher.base.DispatcherBase;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.IOfficialAccountSrvs;

public class AdvertisementDispatcher extends DispatcherBase {

	private static Logger log = Logger.getLogger(AdvertisementDispatcher.class);

	@Autowired
	private IAdvertisementSrvs advertisementSrv;

	@Autowired
	private IOfficialAccountSrvs officialAccountSrv;
	
	public void process(Message message) throws Exception {
		String body = message.getBody();
		AdvertisementSqs req = ThreadLocalUtil.getAppObjectMapper().readValue(body, AdvertisementSqs.class);
 		sendAdvertisement(req);
	}

	private AdvertisementDispatch_req getRequest(AdvertisementSqs reqSqs) {

		AdvertisementDispatch_req req = new AdvertisementDispatch_req();
		AdvertisementDispatch_req.Header header = new AdvertisementDispatch_req.Header();
		AdvertisementDispatch_req.Body body = new AdvertisementDispatch_req.Body();

		body.setId(reqSqs.getUser_id_pro());
		body.setAdv_id(reqSqs.getAds_id());
		body.setContent(reqSqs.getContent());
		body.setContent_thumb(reqSqs.getContent_thumb());
		body.setPublish_timestamp(reqSqs.getTime());
		body.setType(reqSqs.getType());

		req.setHeader(header);
		req.setBody(body);

		header.setCategory("2");

		return req;
	}

	private void sendAdvertisement(AdvertisementSqs req) throws Exception {

 		long adsId = req.getAds_id();
 		long time = req.getTime();
 		int shardingFlag = ShardingUtil.getMonthSharding(new Timestamp(time));
 		
		long userIdPro = req.getUser_id_pro();
		String userIdProPublic = Constant.NI_USER_PUBLIC_PREFIX + userIdPro;

		ObjectMapper om = ThreadLocalUtil.getAppObjectMapper();
		String bodyStr = om.writeValueAsString(getRequest(req));
		SendBatchMsgFieldBody_req bodyObj = new SendBatchMsgFieldBody_req();
		bodyObj.setMsg(bodyStr);
		String body = om.writeValueAsString(bodyObj);

		if (req.getType() == Constant.ADS_TYPE.PIC) {
			S3Util.getThumbObject(req.getContent());
		}
		boolean ret = advertisementSrv.updateAdvertisementSendingState(adsId, shardingFlag);
		log.info("updateAdvertisementSendingState ret:" + ret);
		if(!ret){
			log.info("updateAdvertisementSendingState- failed: ads_id, shardingFlag" + adsId + "--" + shardingFlag);
			return;
		}
		AdsDbEnum.ContentState cstate = AdsDbEnum.ContentState.SEND_SUCCESS;
		try {
			for (int i = 1;; i++) {
				List<QryFollowVO> followlst = officialAccountSrv.queryFollowListByAdserId(userIdPro, i);
				if (followlst.size() == 0) {
					break;
				}
				log.info("followlst size:" + followlst.size());
				StringBuilder sb = new StringBuilder("[");
				for (QryFollowVO qfv : followlst) {
					if (sb.length() != 1) {
						sb.append(",");
					}
					sb.append("\"").append(qfv.getUser_id()).append("\"");
				}
				sb.append("]");
				
				AdsDbEnum.SendLogState state = null;
				try {
					SendBatchMsg_req batchMessage = new SendBatchMsg_req();
					batchMessage.setFromAccid(userIdProPublic);
					batchMessage.setToAccids(sb.toString());
					batchMessage.setBody(body);
					batchMessage.setType("0");
					batchMessage.setPushcontent("a new message");
					
					Timestamp sysdate = commonSrv.querySysdate();
					NiUtil.sendBatchMessage(batchMessage, sysdate);
					state = AdsDbEnum.SendLogState.SEND_SUCCESS;
					
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					state = AdsDbEnum.SendLogState.ERROR;
					cstate = AdsDbEnum.ContentState.ERROR;
				} finally {
					advertisementSrv.saveAdvertisementSendLogList_master(adsId, followlst, state, shardingFlag, i);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			cstate = AdsDbEnum.ContentState.ERROR;
		}finally{
			advertisementSrv.updateAdvertisementState(adsId, cstate.val(), shardingFlag);
		}
	}

	public void init() {
		String sqsUrlName = SysParamCodeDbEnum.SQS_ADVERTISEMENT_URL.getParamCode();
		int threadCount = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.DISPATCHER_ADVERTISEMENT_THREAD_COUNT
				.getParamCode());
		this.setSqsUrlName(sqsUrlName);
		this.setThreadCount(threadCount);
	}

	public static void main(String args[]) {

	}
}
