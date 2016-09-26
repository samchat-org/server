package com.samchat.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLog;
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
	
	public void test() throws Exception{
		//updateAdvertisementSendLog(long logId, Timestamp senddate, byte state, String clientId, String remark,
//		advertisementSrv.queryAdvertisementSendLog( 12, 201609);
		advertisementSrv.updateAdvertisementSendLog(7, new Timestamp(new Date().getTime()), (byte)2, "12345", "123",201609,4);
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
