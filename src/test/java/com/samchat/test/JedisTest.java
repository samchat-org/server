package com.samchat.test;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

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
	
	public void test() throws Exception{
 		testSrvs.insertDict();
 	}
	
	public static void main(String args[]) throws Exception{
//		GregorianCalendar gc=new GregorianCalendar();
//		gc.setTime(new Date());
// 		for(int i = 0; i < 24; i++){
//			gc.add(Calendar.HOUR_OF_DAY, -1);   
//			System.out.println(new SimpleDateFormat("yyyyMMddHH").format(gc.getTime()));
//		}
		ApplicationContext ctx = SpringUtil.initContext("config/spring");
		JedisTest jedisTest = (JedisTest)ctx.getBean("jedisTest");
		jedisTest.test();

	}
}
