package com.samchat.test;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.samchat.common.datas.DbContextHolder;
import com.samchat.common.enums.Constant;
import com.samchat.common.utils.SpringUtil;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;

public class JedisTest {
	
	private static Logger log = Logger.getLogger(JedisTest.class);
	
	@Autowired
	IUserRedisDao userRedisDao12;
	
	@Autowired
	private IQuestionSrvs questionSrv;
	
	@Autowired
	private IAdvertisementSrvs advertisementSrv;
	
	public void test(){
//		QuestionSqs req = new QuestionSqs();
//		req.setQuestion_id(121212121L);
//		questionSrv.saveQuestion(req);
		/**
		 * long adsId, long userIdPro, long userId, byte type,
			String content, String thumb, Timestamp senddate, Timestamp recvdate, byte state, String remark
		 */
		DbContextHolder.setDbType(Constant.DATA_SOURCE.S_MAIN);
		long time = System.currentTimeMillis();
		advertisementSrv.saveAdvertisement(time, 1112, 11111111, (byte)1, "", "", new Timestamp(time ), new Timestamp(time), (byte)1, "");
	}
	
	public static void main(String args[]) throws Exception{
		
		ApplicationContext ctx = SpringUtil.initContext("config/spring");
		JedisTest jedisTest = (JedisTest)ctx.getBean("jedisTest");
		jedisTest.test();
		
	}
}
