package com.samchat.common.utils;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SqsUtil {
	
	private static void pushMessageInner(Object obj, String queueSys, int delay) throws Exception{
		
		AmazonSQS asqs = new AmazonSQSClient();
		String queueUrl = CommonUtil.getSysConfigStr(queueSys);
		ObjectMapper om = ThreadLocalUtil.getAppObjectMapper();
 		
 		SendMessageRequest req = new SendMessageRequest();
 		req.setMessageBody(om.writeValueAsString(obj));
 		req.setQueueUrl(queueUrl);
 		req.setDelaySeconds(delay * 60);
 		asqs.sendMessage(req);
	}
	
	public static void pushMessage(Object obj, String queueSys, String delaySys) throws Exception {
		int delay = CommonUtil.getSysConfigInt(delaySys);
		pushMessageInner(obj, queueSys, delay);
	}
	
	public static void pushMessage(Object obj, String queueSys) throws Exception{
		pushMessageInner(obj, queueSys, 0);
	} 
	
	public static void main(String[] args) throws Exception{}
}
