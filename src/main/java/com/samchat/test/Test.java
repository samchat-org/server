package com.samchat.test;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.enums.Constant;
import com.samchat.common.thread.SysConfigRefreshThread;
import com.samchat.common.utils.GooglePlaceUtil;
import com.samchat.common.utils.HttpclientUrlUtil;
import com.samchat.common.utils.Md5Util;
import com.samchat.common.utils.SpringUtil;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.IOfficialAccountSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class Test {

	private static Logger log = Logger.getLogger(Test.class);

	@Autowired
	IUserRedisDao userRedisDao12;

	@Autowired
	private IQuestionSrvs questionSrv;

	@Autowired
	private IAdvertisementSrvs advertisementSrv;

 
	@Autowired
	private IUsersSrvs usersSrvs;
	@Autowired
	IOfficialAccountSrvs officialAccountSrvs;

	public void neteaseRegister(int count) throws Exception {
		for(int i = 1; i <=2048; i++){
			TUserUsers u = usersSrvs.queryUserInfoByUserName("test" + i);
			usersSrvs.niRegister(u.getUser_id(), u.getUser_name(), Md5Util.getSign4String(u.getUser_name()), new Timestamp(new Date().getTime()));
		} 		 
	}
	
	public static void login(String url) throws Exception{
		for(int i = 1; i <=2048; i++){
			try {
				String userName = "test" + i;
				String urltmp = HttpclientUrlUtil.encodeQuery( url.replaceAll("test_name", userName));
				CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = null;
				try {
					HttpPost httpPost = new HttpPost(urltmp);
					response = httpClient.execute(httpPost);
					int statusCode = response.getStatusLine().getStatusCode();
					if (statusCode != HttpStatus.SC_OK) {
						throw new Exception("访问异常:" + response.getStatusLine().getReasonPhrase());
					}
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String body = EntityUtils.toString(entity, Constant.CHARSET);
						log.info("ni res body:" + body);
					}
					EntityUtils.consume(entity);

				} finally {
					if (response != null) {
						response.close();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) throws Exception {
//		ApplicationContext ctx = SpringUtil.initContext("config/spring");
//		Test j = (Test)ctx.getBean("test");
//		SysConfigRefreshThread refresh = (SysConfigRefreshThread) ctx.getBean("sysConfigRefreshThread");
//		refresh.run();
//		refresh.start();
		 login(args[0]);
	 
	}
}
