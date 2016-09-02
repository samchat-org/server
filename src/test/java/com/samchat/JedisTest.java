package com.samchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.samchat.common.utils.SpringUtil;
import com.samchat.dao.redis.interfaces.IUserRedisDao;

public class JedisTest {
	
	@Autowired
	IUserRedisDao userRedisDao12;
	
	public void test(){
		
	}
	
	public static void main(String args[]) throws Exception{
		ApplicationContext ctx = SpringUtil.initContext("config/spring");
		JedisTest jedisTest = (JedisTest)ctx.getBean("jedisTest");
		jedisTest.test();
	}
}
