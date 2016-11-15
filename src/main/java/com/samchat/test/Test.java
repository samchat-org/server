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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsersExample;
import com.samchat.common.beans.auto.db.mapper.TUserUsersMapper;
import com.samchat.common.beans.auto.json.ni.msg.SendBatchMsgFieldOption_req;
import com.samchat.common.beans.auto.json.ni.msg.SendBatchMsg_req;
import com.samchat.common.enums.Constant;
import com.samchat.common.thread.SysConfigRefreshThread;
import com.samchat.common.utils.HttpclientUrlUtil;
import com.samchat.common.utils.Md5Util;
import com.samchat.common.utils.SpringUtil;
import com.samchat.common.utils.ThreadLocalUtil;
import com.samchat.common.utils.niUtils.NiPostClient;
import com.samchat.common.utils.niUtils.NiUtil;
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
		Byte b = new Byte("1");
		System.out.print(b == 1);
//		ApplicationContext ctx = SpringUtil.initContext("config/spring");
//		
//		SysConfigRefreshThread refresh = (SysConfigRefreshThread) ctx.getBean("sysConfigRefreshThread");
//		refresh.run();
 //		
//		
//		QuestionDispatcher ads = (QuestionDispatcher) ctx.getBean("questionDispatcher");
//		 
//		Message msg = new Message();
//		msg.setBody(args[0]);
//		ads.process(msg);
		
		
//		SendBatchMsg_req batchMessage = new SendBatchMsg_req();
//		batchMessage.setFromAccid("public_10000002130");
//		batchMessage.setToAccids("[\"10000002126\",\"10000002127\"]");
//		String body = "{\"msg\":{\"header\":{\"category\":\"2\"},\"body\":{\"id\":10000002130,\"adv_id\":{adv_id},\"publish_timestamp\":1478841149000,\"type\":0,\"content\":\"Amazon SQS and Amazon CloudWatch are integrated so you can use CloudWatch to easily view and analyze metrics for your Amazon SQS queues. You can view and analyze your queues' metrics from the Amazon SQS console, the CloudWatch console, the command line, or programmatically.CloudWatch metrics for your Amazon SQS queues are automatically collected and pushed to CloudWatch every five minutes. (Detailed monitoring, or one-minute metrics, is currently unavailable for Amazon SQS.) These metrics are gathered on all queues that meet the CloudWatch guidelines for being active. A queue is considered active by CloudWatch for up to six hours from the last activity (for example, any API call) on the queue. \",\"content_thumb\":\"\"}}}";
////		batchMessage.setBody("{\"msg\":{\"header\":{\"category\":\"2\"},\"body\":{\"id\":10000002130,\"adv_id\":{adv_id},\"publish_timestamp\":1478841149000,\"type\":0,\"content\":\"Amazon SQS and Amazon CloudWatch are integrated so you can use CloudWatch to easily view and analyze metrics for your Amazon SQS queues. You can view and analyze your queues' metrics from the Amazon SQS console, the CloudWatch console, the command line, or programmatically.CloudWatch metrics for your Amazon SQS queues are automatically collected and pushed to CloudWatch every five minutes. (Detailed monitoring, or one-minute metrics, is currently unavailable for Amazon SQS.) These metrics are gathered on all queues that meet the CloudWatch guidelines for being active. A queue is considered active by CloudWatch for up to six hours from the last activity (for example, any API call) on the queue. \",\"content_thumb\":\"\"}}}");
//		batchMessage.setType("0");
//		batchMessage.setPushcontent("a new message");
// 		
//		int error = 0;
//		for(int i = 10000680; i < 10001680; i ++){
//			try {
//				batchMessage.setBody(body.replaceAll("\\{adv_id\\}", i + ""));
//				NiUtil.sendBatchMessage(batchMessage, new Timestamp(new Date().getTime()));
//			} catch (Exception e) {
//				error ++;
//			}
//		}
//		log.error("error_count:" + error);
//		
// 		String url = "https://api.netease.im/nimserver/msg/sendBatchMsg.action";
// 		
// 		ObjectMapper om = ThreadLocalUtil.getAppObjectMapper();
//		SendBatchMsgFieldOption_req s = new SendBatchMsgFieldOption_req();
//		s.setRoam(false);        
//		s.setHistory(true);     
//		s.setSendersync(true);  
//		s.setPush(true);        
//		s.setRoute(true);       
//		s.setBadge(true);       
//		s.setNeedPushNick(true);
//
//		String option =om.writeValueAsString(s);
// 		
//		Map<Object, Object> param = new HashMap<Object, Object>(); 
//		param.put("fromAccid", "public_10000002130");
//		param.put("toAccids", "[\"10000002126\",\"10000002127\"]");
//		param.put("type", "0");
//		param.put("body", "{\"msg\":\"hello123456\"}");
//		param.put("pushcontent", "a new message");
//		param.put("option", option);
//		log.info("start--");
//		for(int i = 0; i < 1000; i++){
//			try {
//				NiPostClient.post(url, param, new Timestamp(new Date().getTime()));
//			} catch (Exception e) {
//				log.error(e.getMessage(), e);
//				Thread.sleep(2000);
//			}
//		}
		
		log.info("end--");
 	}
}
