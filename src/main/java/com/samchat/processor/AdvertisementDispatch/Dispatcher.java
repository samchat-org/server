package com.samchat.processor.AdvertisementDispatch;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContent;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLog;
import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDispatch_req;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.cache.UserInfoFieldRdsEnum;
import com.samchat.common.enums.db.AdsDbEnum;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.GetuiUtil;
import com.samchat.common.utils.S3Util;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.ICommonSrvm;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IOfficialAccountSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class Dispatcher extends Thread {

	private static Logger log = Logger.getLogger(Dispatcher.class);

	@Autowired
	private ICommonSrvs commonSrv;

	@Autowired
	private ICommonSrvm commonSrvm;

	@Autowired
	private IAdvertisementSrvs advertisementSrv;

	@Autowired
	private IUsersSrvs usersSrv;

	@Autowired
	private IOfficialAccountSrvs officialAccountSrv;

	private ObjectMapper om = null;

	private AmazonSQS asqs;

	public void paramInit() {
		try {
			asqs = new AmazonSQSClient();
			om = new ObjectMapper();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Error();
		}
	}

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

	private AdvertisementDispatch_req getRequest(long testId, AdvertisementSqs reqSqs) {

		AdvertisementDispatch_req req = new AdvertisementDispatch_req();
		AdvertisementDispatch_req.Header header = new AdvertisementDispatch_req.Header();
		AdvertisementDispatch_req.Body body = new AdvertisementDispatch_req.Body();

		body.setId(reqSqs.getUser_id_pro());
		body.setAdv_id(reqSqs.getAds_id());
		body.setContent(reqSqs.getContent());
		body.setContent_thumb(reqSqs.getContent_thumb());
		body.setDest_id(testId);
		body.setPublish_timestamp(reqSqs.getTime());
		body.setType(reqSqs.getType());

		req.setHeader(header);
		req.setBody(body);

		header.setCategory("2");

		return req;
	}

	public void resendAdvertisement(AdvertisementSqs req) throws Exception {
		long userId = req.getUser_id();
 		int validCycle = CommonUtil.getSysConfigInt("aws_sqs_advertisement_valid_cycle");

		Timestamp sysdate = commonSrv.querySysdate();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(sysdate);
		
		for (int i = 0; i < validCycle; i++) {
			int shardingFlag = Integer.parseInt(Constant.SDF_YYYYMM.format(gc.getTime()));
			List<TAdvertisementSendLog> sendLogs = advertisementSrv.queryAdvertisementSendLog(userId, shardingFlag);

			for (TAdvertisementSendLog sendlog : sendLogs) {
				long adsId = sendlog.getAds_id();
				TAdvertisementContent content = advertisementSrv.queryAdvertisementCotentById(adsId, shardingFlag);
				if (content == null) {
					continue;
				}
				log.info("content:" + content.getContent());
				String clientId = sendlog.getClient_id();
 				int sendcount = sendlog.getSend_count();
				
 				String curClientId = commonSrv.hgetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.CLIENT_ID.val());
		
				log.info("old clientId:" + clientId + "--new clientId:" + curClientId + "--sysdate:" + sysdate);
				byte state = AdsDbEnum.SendLogState.SEND_SUCCESS.val();
				String remark = AdsDbEnum.SendLogState.SEND_SUCCESS.name();
				String pushRst = "";
				try {
					String dispatchReq = om.writeValueAsString(getRequest(sendlog, content));
					pushRst = GetuiUtil.push(userId + "", dispatchReq);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					state = AdsDbEnum.SendLogState.ERROR.val();
					remark = AdsDbEnum.SendLogState.ERROR.name();
				} finally {
					advertisementSrv.updateAdvertisementSendLog(sendlog.getLog_id(), new Timestamp(sysdate.getTime()),
							state, curClientId, remark + "," + pushRst, shardingFlag, sendcount + 1);
				}
			}
			gc.add(Calendar.MONTH, -1);
		}

	}

	public void sendAdvertisement(AdvertisementSqs req) throws Exception {

		long adsId = req.getAds_id();
		long userIdPro = req.getUser_id_pro();

		if (req.getType() == Constant.ADS_TYPE.PIC) {
			req.setContent_thumb(S3Util.getThumbObject(req.getContent()));
		}
		int shardingFlag = Integer.parseInt(Constant.SDF_YYYYMM.format(new Timestamp(req.getTime())));
		advertisementSrv.saveAdvertisementContent(adsId, userIdPro, (byte) req.getType(), req.getContent(),
				req.getContent_thumb(), new Timestamp(req.getTime()), shardingFlag);

		List<TOaFollow> followlst = officialAccountSrv.queryFollowListByAdserId(userIdPro);
		Timestamp senddate = commonSrv.querySysdate();

		for (TOaFollow ff : followlst) {
			long userId = ff.getUser_id();
			String curClientId = commonSrv.hgetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.CLIENT_ID.val());
			byte state = AdsDbEnum.SendLogState.SEND_SUCCESS.val();
			String remark = AdsDbEnum.SendLogState.SEND_SUCCESS.name();
			String pushRst = "";
			try {
				String dispatchReq = om.writeValueAsString(getRequest(userId, req));
				pushRst = GetuiUtil.push(userId + "", dispatchReq);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				state = AdsDbEnum.SendLogState.ERROR.val();
				remark = AdsDbEnum.SendLogState.ERROR.name();
			} finally {
				advertisementSrv.saveAdvertisementSendLog(adsId, userId, senddate, state, curClientId, remark + ","
						+ pushRst, shardingFlag);
			}
		}
	}

	public void run() {
		paramInit();
		for (;;) {
			try {
				String queueUrl = CommonUtil.getSysConfigStr("aws_sqs_advertisement_url");
				ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);

				int watiTime = CommonUtil.getSysConfigInt("aws_sqs_receive_wait_time");
				int visibilityTime = CommonUtil.getSysConfigInt("aws_sqs_receive_visibility_time");
				receiveMessageRequest.setWaitTimeSeconds(watiTime);
				receiveMessageRequest.setVisibilityTimeout(visibilityTime);

				log.info("recving---");
				List<Message> messages = asqs.receiveMessage(receiveMessageRequest).getMessages();
				for (Message message : messages) {
					String body = message.getBody();
					log.info("messages body:" + body);
					try {
						AdvertisementSqs req = om.readValue(body, AdvertisementSqs.class);
						long sendType = req.getSendType();
						if (sendType == 2) {
							sendAdvertisement(req);
						} else if (sendType == 1) {
							resendAdvertisement(req);
						}
					} catch (Exception e) {
						log.error("error message:" + body, e);
					} finally {
						asqs.deleteMessage(queueUrl, message.getReceiptHandle());
					}
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	public static void main(String args[]) {

	}
}
