package com.samchat.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.datas.DbContextHolder;
import com.samchat.common.enums.Constant;
import com.samchat.common.utils.CommonUtil;
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
	
	public void test() throws Exception{
//		QuestionSqs req = new QuestionSqs();
//		req.setQuestion_id(121212121L);
//		questionSrv.saveQuestion(req);
		/**
		 * long adsId, long userIdPro, long userId, byte type,
			String content, String thumb, Timestamp senddate, Timestamp recvdate, byte state, String remark
		 */
		DbContextHolder.setDbType(Constant.DATA_SOURCE.S_MAIN);
		long time = System.currentTimeMillis();
//		advertisementSrv.queryAdvertisementSendLog(1,null);
	}
	
	public static void main(String args[]) throws Exception{
//		GregorianCalendar gc=new GregorianCalendar();
//		gc.setTime(new Date());
// 		for(int i = 0; i < 24; i++){
//			gc.add(Calendar.HOUR_OF_DAY, -1);   
//			System.out.println(new SimpleDateFormat("yyyyMMddHH").format(gc.getTime()));
//		}
//		ApplicationContext ctx = SpringUtil.initContext("config/spring");
//		JedisTest jedisTest = (JedisTest)ctx.getBean("jedisTest");
//		jedisTest.test();
		Date lastDate = CommonUtil.operationHourForDate(new Date(), -20);
		System.out.print(new Date().after(lastDate));
	}
}
