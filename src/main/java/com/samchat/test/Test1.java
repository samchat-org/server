package com.samchat.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;
import com.samchat.common.beans.manual.json.sqs.SysMsgSqs;

public class Test1 {
	public static void main(String[] args) throws Exception{
		
		
		
		SysMsgSqs  sqs = new SysMsgSqs();
		sqs.setMsgType(1);
		
		QuestionSqs qs = new QuestionSqs();
		qs.setAddress("aaaaaaa");
		qs.setPlace_id("adbfabf,madbfa,m");
		sqs.setMsgObj(qs);
		
 		
		ObjectMapper om = new ObjectMapper();
//		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		om.enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
		UserInfoRds r = om.readValue(args[0], UserInfoRds.class);
		
		ObjectMapper om1 = new ObjectMapper();
		String va = om1.writeValueAsString(r);
		om1.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		om1.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		r = om1.readValue(args[0], UserInfoRds.class);
		
  		System.out.print(2);
	}
}
