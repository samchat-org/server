package com.samchat.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.json.appserver.question.Question_req;
import com.samchat.common.beans.auto.json.appserver.question.Question_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;
import com.samchat.service.interfaces.IQuestionSrv;

public class QuestionAction extends BaseAction {

	@Autowired
	private IQuestionSrv questionSrv;

	public Question_res question(Question_req req, TokenRds token) throws Exception {

		QuestionSqs qs = questionSrv.sendQuestion(req, token);
		Question_res res = new Question_res();
		res.setRet(Constant.SUCCESS);
		res.setQuestion_id(qs.getQuestion_id());
		res.setDatetime(qs.getTime());
		return res;
	}

	public void questionValidate(Question_req req, TokenRds token) {
	}
}
