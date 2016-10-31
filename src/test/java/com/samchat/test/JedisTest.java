package com.samchat.test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.samchat.common.thread.SysConfigRefreshThread;
import com.samchat.common.utils.GooglePlaceUtil;
import com.samchat.common.utils.SpringUtil;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;
import com.samchat.service.interfaces.ITestSrvs;

public class JedisTest {

	private static Logger log = Logger.getLogger(JedisTest.class);

	@Autowired
	IUserRedisDao userRedisDao12;

	@Autowired
	private IQuestionSrvs questionSrv;

	@Autowired
	private IAdvertisementSrvs advertisementSrv;

	@Autowired
	private ITestSrvs testSrvs;

	public void test() throws Exception {
		GooglePlaceUtil.autocomplete("abcd");
	}

	public static void main(String args[]) throws Exception {
		ApplicationContext ctx = SpringUtil.initContext("config/spring");
		JedisTest j = (JedisTest)ctx.getBean("jedisTest");
		SysConfigRefreshThread refresh = (SysConfigRefreshThread) ctx.getBean("sysConfigRefreshThread");
		refresh.run();
		refresh.start();
		j.test();
		 
	}
}
