package com.samchat.test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.samchat.action.UserAction;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;
import com.samchat.common.utils.SpringUtil;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IQuestionSrvs;

public class JedisTest {
	
	private static Logger log = Logger.getLogger(JedisTest.class);
	
	@Autowired
	IUserRedisDao userRedisDao12;
	
	@Autowired
	private IQuestionSrvs questionSrv;
	
	public void test(){
		QuestionSqs req = new QuestionSqs();
		req.setQuestion_id(121212121L);
		questionSrv.saveQuestion(req);
	}
	
	public static void main(String args[]) throws Exception{
		
		ApplicationContext ctx = SpringUtil.initContext("config/spring");
		JedisTest jedisTest = (JedisTest)ctx.getBean("jedisTest");
		jedisTest.test();
		
	}
}
