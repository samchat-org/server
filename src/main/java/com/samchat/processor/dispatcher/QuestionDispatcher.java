package com.samchat.processor.dispatcher;

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
import com.samchat.processor.dispatcher.base.DispatcherBase;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class QuestionDispatcher extends DispatcherBase {

	private static Logger log = Logger.getLogger(QuestionDispatcher.class);

	@Autowired
	private IQuestionSrvs questionSrv;

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

	protected void process(Message message) throws Exception {

		String body = message.getBody();
		QuestionSqs req = om.readValue(body, QuestionSqs.class);
		questionSrv.saveQuestion(req);
		List<TUserUsers> users = usersSrv.queryUsers();
		for (TUserUsers user : users) {
			if (user.getUser_id() != req.getUser_id()) {
				String dispatchReq = om.writeValueAsString(getRequest(user, req));
				GetuiUtil.push(user.getUser_id().toString(), dispatchReq);
			}
		}
	}

	public static void main(String args[]) {

	}
}
