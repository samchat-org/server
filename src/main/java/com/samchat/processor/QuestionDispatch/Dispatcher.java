package com.samchat.processor.QuestionDispatch;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.question.DispatchQuestion_req;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.GetuiUtil;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class Dispatcher extends Thread {

	private static Logger log = Logger.getLogger(Dispatcher.class);

	@Autowired
	private ICommonSrvs commonSrv;

	@Autowired
	private IQuestionSrvs questionSrv;

	@Autowired
	private IUsersSrvs usersSrv;

	private ObjectMapper om = null;

	private AmazonSQS asqs;

	public void paramInit() {
		String accessKey = CommonUtil.getSysConfigStr("aws_access_key");
		String secretKey = CommonUtil.getSysConfigStr("aws_secret_key");
		asqs = new AmazonSQSClient(new BasicAWSCredentials(accessKey, secretKey));
		om = new ObjectMapper();
	}

	private DispatchQuestion_req getRequest(TUserUsers destUser, QuestionSqs reqSqs) {

		DispatchQuestion_req req = new DispatchQuestion_req();
		DispatchQuestion_req.Header header = new DispatchQuestion_req.Header();
		DispatchQuestion_req.Body body = new DispatchQuestion_req.Body();
		DispatchQuestion_req.User user = new DispatchQuestion_req.User();

		req.setHeader(header);
		req.setBody(body);
		body.setUser(user);

		header.setCategory("1");

		body.setDatetime(reqSqs.getTime());
		body.setQuestion_id(reqSqs.getQuestion_id());
		body.setQuestion(reqSqs.getQuestion());
		body.setOpt(reqSqs.getOpt());
		body.setAddress(reqSqs.getAddress());
		user.setId(reqSqs.getUser_id());

		TUserUsers userSrc = usersSrv.queryUser(reqSqs.getUser_id());
		user.setUsername(userSrc.getUser_name());
		user.setLastupdate(userSrc.getState_date().getTime());

		body.setDest_id(destUser.getUser_id());

		return req;
	}

	public void run() {
		paramInit();
		for (;;) {
			try {
				String queueUrl = CommonUtil.getSysConfigStr("aws_sqs_question_url");
				ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
				
				int watiTime = CommonUtil.getSysConfigInt("aws_sqs_receive_wait_time");
				int visibilityTime = CommonUtil.getSysConfigInt("aws_sqs_receive_visibility_time");
				receiveMessageRequest.setWaitTimeSeconds(watiTime);
				receiveMessageRequest.setVisibilityTimeout(visibilityTime);
				
				log.info("recving---");
				List<Message> messages = asqs.receiveMessage(receiveMessageRequest).getMessages();
 				for (Message message : messages) {
					String body = message.getBody();
					log.info("messages body:" + body);
					try {
						QuestionSqs req = om.readValue(body, QuestionSqs.class);
						questionSrv.saveQuestion(req);
 						List<TUserUsers> users = usersSrv.queryUsers();
						for (TUserUsers user : users) {
							String dispatchReq = om.writeValueAsString(getRequest(user, req));
 							GetuiUtil.push(user.getUser_id().toString(), dispatchReq);
						}
					} catch (Exception e) {
						log.error("error message:" + body, e);
					} finally{
						asqs.deleteMessage(queueUrl, message.getReceiptHandle());
					}
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
	}
	
	public static void main(String args[]){
		
	}
}
