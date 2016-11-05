package com.samchat.processor.dispatcher.base;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.exceptions.SysException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public abstract class DispatcherBase {

	private static Logger log = Logger.getLogger(DispatcherBase.class);

	private String sqsUrlName;

	private int threadCount;

	@Autowired
	protected ICommonSrvs commonSrv;

	@Autowired
	protected IQuestionSrvs questionSrv;

	@Autowired
	protected IUsersSrvs usersSrv;

	protected DispatcherBase() {

	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public void setSqsUrlName(String sqsUrlName) {
		this.sqsUrlName = sqsUrlName;
	}

	public void multipleThreadsRun() {
		init();
		paramCheck();
		ExecutorService es = Executors.newFixedThreadPool(threadCount);
		for (int i = 0; i < threadCount; i++) {
			es.execute(new Runnable() {
				public void run() {
					log.info(this + ":thread start--");
					AmazonSQS asqs = new AmazonSQSClient();
					for (;;) {
						try {
							singleThreadRun(asqs);
						} catch (Exception e) {
							log.error(e);
							try {
								log.info("sleep 5s");
								Thread.sleep(5000L);
							} catch (InterruptedException e1) {
							}
						}
					}
				}
			});
		}
	}

	public void singleThreadRun(AmazonSQS asqs) throws Exception {

		String queueUrl = CommonUtil.getSysConfigStr(sqsUrlName);
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);

		int watiTime = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.SQS_RECEIVE_WAIT_TIME.getParamCode());
		int visibilityTime = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.SQS_RECEIVE_VISIBILITY_TIME.getParamCode());
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
	}

	private void paramCheck() {
		if (threadCount <= 0) {
			throw new SysException("dispatcher threadCount <= 0 :" + threadCount);
		}
		if (StringUtils.trimToNull(sqsUrlName) == null) {
			throw new SysException("dispatcher sqsUrlName is null :" + sqsUrlName);
		}
	}

	protected abstract void init();

	public abstract void process(Message message) throws Exception;

}
