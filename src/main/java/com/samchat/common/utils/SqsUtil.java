package com.samchat.common.utils;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SqsUtil {
	public static void pushMessage(Object obj, String queueSys) throws Exception {
		
		AmazonSQS asqs = new AmazonSQSClient();
		String queueUrl = CommonUtil.getSysConfigStr(queueSys);
		ObjectMapper om = ThreadLocalUtil.getAppObjectMapper();
		asqs.sendMessage(queueUrl, om.writeValueAsString(obj));

	}
	
	public static void main(String[] args) throws Exception{}
}
