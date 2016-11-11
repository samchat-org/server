package com.samchat.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

import com.amazonaws.services.sqs.model.Message;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsersExample;
import com.samchat.common.beans.auto.db.mapper.TUserUsersMapper;
import com.samchat.common.enums.Constant;
import com.samchat.common.thread.SysConfigRefreshThread;
import com.samchat.common.utils.HttpclientUrlUtil;
import com.samchat.common.utils.Md5Util;
import com.samchat.common.utils.SpringUtil;
import com.samchat.common.utils.niUtils.NiPostClient;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.processor.dispatcher.AdvertisementDispatcher;
import com.samchat.processor.dispatcher.QuestionDispatcher;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.IOfficialAccountSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class Test {

	private static Logger log = Logger.getLogger(Test.class);
	
	@Autowired
	private TUserUsersMapper userUsersMapper;

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
		for (int i = 1; i <= 2048; i++) {
			TUserUsers u = usersSrvs.queryUserInfoByUserName("test" + i);
			usersSrvs.niRegister(u.getUser_id() + "", u.getUser_name(), Md5Util.getSign4String(u.getUser_name()),
					new Timestamp(new Date().getTime()));
		}
	}

	public List<TUserUsers> queryAllUser(){
		TUserUsersExample uue = new TUserUsersExample();
		uue.createCriteria().andUser_typeEqualTo(Constant.USER_TYPE_CUSTOMER).andStateEqualTo(Constant.STATE_IN_USE);
		return userUsersMapper.selectByExample(uue);
	}
	
	public void adsDisTest(){
		
	}

	public static void login(String url) throws Exception {
		for (int i = 1; i <= 2048; i++) {
			try {
				String userName = "test" + i;
				String urltmp = HttpclientUrlUtil.encodeQuery(url.replaceAll("test_name", userName));
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

	public void init() throws Exception {
 	}
	
	public static void pushMsg() throws Exception{
		String url = "https://api.netease.im/nimserver/msg/sendAttachMsg.action";
		Map<Object, Object> param = new HashMap<Object, Object>(); 
		param.put("from", "10000000074");
		param.put("msgtype", 0);
		param.put("to", "10000000075");
		param.put("attach", "abcd1234");
		
		for(int i = 0; i < 20; i++){
 			param.put("attach", "abcd1235" + i);
			NiPostClient.post(url, param, new Timestamp(new Date().getTime()));
  		}
	}

	public static void main(String args[]) throws Exception {
		ApplicationContext ctx = SpringUtil.initContext("config/spring");
		
		SysConfigRefreshThread refresh = (SysConfigRefreshThread) ctx.getBean("sysConfigRefreshThread");
		refresh.run();
		refresh.start();
		
		
		QuestionDispatcher ads = (QuestionDispatcher) ctx.getBean("questionDispatcher");
		 
		Message msg = new Message();
		msg.setBody(args[0]);
		ads.process(msg);
 //		String url = "https://api.netease.im/nimserver/msg/sendBatchMsg.action";
//		Map<Object, Object> param = new HashMap<Object, Object>(); 
//		param.put("fromAccid", "10000002116");
//		param.put("toAccids", "[\"10000000069\",\"10000000070\",\"10000000071\",\"10000000072\",\"10000000073\",\"10000000074\",\"10000000075\",\"10000000076\",\"10000000077\",\"10000000078\",\"10000000079\",\"10000000080\",\"10000000081\",\"10000000082\",\"10000000083\",\"10000000084\",\"10000000085\",\"10000000086\",\"10000000087\",\"10000000088\",\"10000000089\",\"10000000090\",\"10000000091\",\"10000000092\",\"10000000093\",\"10000000094\",\"10000000095\",\"10000000096\",\"10000000097\",\"10000000098\",\"10000000099\",\"10000000100\",\"10000000101\",\"10000000102\",\"10000000103\",\"10000000104\",\"10000000105\",\"10000000106\",\"10000000107\",\"10000000108\",\"10000000109\",\"10000000110\",\"10000000111\",\"10000000112\",\"10000000113\",\"10000000114\",\"10000000115\",\"10000000116\",\"10000000117\",\"10000000118\",\"10000000119\",\"10000000120\",\"10000000121\",\"10000000122\",\"10000000123\",\"10000000124\",\"10000000125\",\"10000000126\",\"10000000127\",\"10000000128\",\"10000000129\",\"10000000130\",\"10000000131\",\"10000000132\",\"10000000133\",\"10000000134\",\"10000000135\",\"10000000136\",\"10000000137\",\"10000000138\",\"10000000139\",\"10000000140\",\"10000000141\",\"10000000142\",\"10000000143\",\"10000000144\",\"10000000145\",\"10000000146\",\"10000000147\",\"10000000148\",\"10000000149\",\"10000000150\",\"10000000151\",\"10000000152\",\"10000000153\",\"10000000154\",\"10000000155\",\"10000000156\",\"10000000157\",\"10000000158\",\"10000000159\",\"10000000160\",\"10000000161\",\"10000000162\",\"10000000163\",\"10000000164\",\"10000000165\",\"10000000166\",\"10000000167\",\"10000000168\"]");
//		param.put("type", "0");
//		param.put("body", "{\"msg\":\"hello123456\"}");
//		log.info("start--");
//		NiPostClient.post(url, param, new Timestamp(new Date().getTime()));
//		log.info("end--");
 	}
}
