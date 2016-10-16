package com.samchat.test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.processor.AdvertisementDispatch.Dispatcher;
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
	
	public void test() throws Exception{
 		testSrvs.insertDict();
 		testSrvs.queryDict();
 	}
	
	public static void main(String args[]) throws Exception{
		long l = System.currentTimeMillis();
		for(int i = 0; i < 100; i++){
			Dispatcher s = new Dispatcher();
		}
		System.out.println(System.currentTimeMillis() - l);
	}
}
