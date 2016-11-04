package com.samchat.processor.dispatcher.base;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.samchat.common.utils.CommonUtil;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public abstract class DispatcherBase extends Thread {

	private static Logger log = Logger.getLogger(DispatcherBase.class);

	private String sqsUrlName;

	@Autowired
	protected ICommonSrvs commonSrv;

	@Autowired
	protected IQuestionSrvs questionSrv;

	@Autowired
	protected IUsersSrvs usersSrv;

	protected ObjectMapper om = null;

	protected AmazonSQS asqs;

	protected DispatcherBase() {
		asqs = new AmazonSQSClient();
		om = new ObjectMapper();
	}

	public void setSqsUrlName(String sqsUrlName) {
		this.sqsUrlName = sqsUrlName;
	}

	public void run() {
		for (;;) {
			try {
				String queueUrl = CommonUtil.getSysConfigStr(sqsUrlName);
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
						process(message);
						asqs.deleteMessage(queueUrl, message.getReceiptHandle());
					} catch (Exception e) {
						log.error("error message:" + body, e);
					}
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	protected abstract void process(Message message) throws Exception;
}
