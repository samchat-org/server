package com.samchat.processor.dispatcher;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContent;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLog;
import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDispatch_req;
import com.samchat.common.beans.auto.json.ni.msg.SendBatchMsgFieldBody_req;
import com.samchat.common.beans.auto.json.ni.msg.SendBatchMsg_req;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.cache.UserInfoFieldRdsEnum;
import com.samchat.common.enums.db.AdsDbEnum;
import com.samchat.common.enums.db.FollowDbEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.GetuiUtil;
import com.samchat.common.utils.S3Util;
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

	private AdvertisementDispatch_req getRequest(TAdvertisementSendLog sendlog, TAdvertisementContent content) {

		AdvertisementDispatch_req req = new AdvertisementDispatch_req();
		AdvertisementDispatch_req.Header header = new AdvertisementDispatch_req.Header();
		AdvertisementDispatch_req.Body body = new AdvertisementDispatch_req.Body();

		body.setId(content.getUser_id_pro());
		body.setAdv_id(sendlog.getAds_id());
		body.setContent(content.getContent());
		body.setContent_thumb(content.getContent_thumb());
		body.setDest_id(sendlog.getUser_id());
		body.setPublish_timestamp(content.getCreate_date().getTime());
		body.setType(content.getAds_type());

		req.setHeader(header);
		req.setBody(body);

		header.setCategory("2");

		return req;
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

	private void resendAdvertisement(AdvertisementSqs req) throws Exception {
		long userId = req.getUser_id();
		int validCycle = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.DISPATCHER_ADVERTISEMENT_VALID_CYCLE
				.getParamCode());

		Timestamp sysdate = commonSrv.querySysdate();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(sysdate);

		for (int i = 0; i < validCycle; i++) {
			int shardingFlag = Integer.parseInt(Constant.SDF_YYYYMM.format(gc.getTime()));
			List<TAdvertisementSendLog> sendLogs = advertisementSrv.queryAdvertisementSendLog(userId, shardingFlag);

			for (TAdvertisementSendLog sendlog : sendLogs) {

				long adsId = sendlog.getAds_id();
				long logId = sendlog.getLog_id();
				String clientId = sendlog.getClient_id();
				int sendcount = sendlog.getSend_count();
				String curClientId = commonSrv.hgetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.CLIENT_ID.val());

				byte state = AdsDbEnum.SendLogState.SEND_SUCCESS.val();
				String remark = AdsDbEnum.SendLogState.SEND_SUCCESS.name();
				String pushRst = "";

				try {
					TAdvertisementContent content = advertisementSrv.queryAdvertisementCotentById(adsId, shardingFlag);
					TOaFollow follow = officialAccountSrv.queryUserFollow(userId, content.getUser_id_pro());

					if (follow == null || content == null || follow.getBlock_tag() == FollowDbEnum.Block.BLOCK.val()) {
						advertisementSrv.updateAdvertisementSendLog(logId, null, AdsDbEnum.SendLogState.CANCEL.val(),
								null, "cancel", shardingFlag, 0);
						continue;
					}
					log.info("content:" + content.getContent());
					log.info("old clientId:" + clientId + "--new clientId:" + curClientId + "--sysdate:" + sysdate);

					String dispatchReq = ThreadLocalUtil.getAppObjectMapper().writeValueAsString(
							getRequest(sendlog, content));
					pushRst = GetuiUtil.push(userId + "", dispatchReq);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					state = AdsDbEnum.SendLogState.ERROR.val();
					remark = AdsDbEnum.SendLogState.ERROR.name();
				} finally {
					advertisementSrv.updateAdvertisementSendLog(logId, new Timestamp(sysdate.getTime()), state,
							curClientId, remark + "," + pushRst, shardingFlag, sendcount + 1);
				}
			}
			gc.add(Calendar.MONTH, -1);
		}

	}

	private void sendAdvertisement(AdvertisementSqs req) throws Exception {

 		long adsId = req.getAds_id();
		long userIdPro = req.getUser_id_pro();
		String userIdProPublic = Constant.NI_USER_PUBLIC_PREFIX + userIdPro;
		Timestamp sysdate = commonSrv.querySysdate();

		ObjectMapper om = ThreadLocalUtil.getAppObjectMapper();
		String bodyStr = om.writeValueAsString(getRequest(req));
		SendBatchMsgFieldBody_req bodyObj = new SendBatchMsgFieldBody_req();
		bodyObj.setMsg(bodyStr);
		String body = om.writeValueAsString(bodyObj);

		if (req.getType() == Constant.ADS_TYPE.PIC) {
			req.setContent_thumb(S3Util.getThumbObject(req.getContent()));
		}

		int shardingFlag = Integer.parseInt(Constant.SDF_YYYYMM.format(new Timestamp(req.getTime())));
		advertisementSrv.saveAdvertisementContent(adsId, userIdPro, (byte) req.getType(), req.getContent(),
				req.getContent_thumb(), new Timestamp(req.getTime()), shardingFlag);

		for (int i = 1;; i++) {
			List<QryFollowVO> followlst = officialAccountSrv.queryFollowListByAdserId(userIdPro, i);
			if (followlst.size() == 0) {
				break;
			}
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
				batchMessage.setPayload(bodyStr);
				NiUtil.sendBatchMessage(batchMessage, sysdate);
				state = AdsDbEnum.SendLogState.RECV_SUCCESS;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				state = AdsDbEnum.SendLogState.ERROR;
			} finally {
				advertisementSrv.saveAdvertisementSendLogList(adsId, followlst, sysdate, state, shardingFlag, i);
			}
		}
	}

	public void process(Message message) throws Exception {

		String body = message.getBody();
		AdvertisementSqs req = ThreadLocalUtil.getAppObjectMapper().readValue(body, AdvertisementSqs.class);
		long sendType = req.getSendType();
		if (sendType == 2) {
			sendAdvertisement(req);
		} else if (sendType == 1) {
			resendAdvertisement(req);
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
