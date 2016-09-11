package com.samchat.common.utils;

import org.codehaus.jackson.map.ObjectMapper;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;

public class SqsUtil {
	public static void pushMessage(Object obj, String queueSys) throws Exception {
		
		AmazonSQS asqs = new AmazonSQSClient();
		String queueUrl = CommonUtil.getSysConfigStr(queueSys);
		ObjectMapper om = new ObjectMapper();
		asqs.sendMessage(queueUrl, om.writeValueAsString(obj));

	}
	
	public static void main(String[] args) throws Exception{}
}
