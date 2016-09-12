package com.samchat.processor.AdvertisementDispatch;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDispatch_req;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.GetuiUtil;
import com.samchat.common.utils.S3Util;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IOfficialAccountSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class Dispatcher extends Thread {

	private static Logger log = Logger.getLogger(Dispatcher.class);

	@Autowired
	private ICommonSrvs commonSrv;

	@Autowired
	private IAdvertisementSrvs advertisementSrv;

	@Autowired
	private IUsersSrvs usersSrv;

	@Autowired
	private IOfficialAccountSrvs officialAccountSrv;

	private ObjectMapper om = null;

	private AmazonSQS asqs;

	public void paramInit() {
		String accessKey = CommonUtil.getSysConfigStr("aws_access_key");
		String secretKey = CommonUtil.getSysConfigStr("aws_secret_key");
		asqs = new AmazonSQSClient(new BasicAWSCredentials(accessKey, secretKey));
		om = new ObjectMapper();
	}

	private AdvertisementDispatch_req getRequest(long testId, AdvertisementSqs reqSqs) {

		AdvertisementDispatch_req req = new AdvertisementDispatch_req();
		AdvertisementDispatch_req.Header header = new AdvertisementDispatch_req.Header();
		AdvertisementDispatch_req.Body body = new AdvertisementDispatch_req.Body();

		body.setId(reqSqs.getUser_id());
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
						req.setContent_thumb(S3Util.getThumbObject(req.getContent()));
 						advertisementSrv.saveAdvertisement(req.getUser_id(), (byte) req.getType(), req.getContent(),
								req.getAds_id(), new Timestamp(req.getTime()));
 						List<TOaFollow> followlst = officialAccountSrv.queryFollowListByAdserId(req.getUser_id());
						for (TOaFollow ff : followlst) {
							String dispatchReq = om.writeValueAsString(getRequest(ff.getUser_id(), req));
							GetuiUtil.push(ff.getUser_id().toString(), dispatchReq);
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
