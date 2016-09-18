package com.samchat.action;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.json.appserver.question.Question_req;
import com.samchat.common.beans.auto.json.appserver.question.Question_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;
import com.samchat.common.enums.Constant;
import com.samchat.service.interfaces.ICommonSrvm;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;

public class QuestionAction extends BaseAction {

	@Autowired
	private IQuestionSrvs questionSrv;
	@Autowired
	private ICommonSrvs commonSrv;
	
	@Autowired
	private ICommonSrvm commonSrvm;

	public Question_res question(Question_req req, TokenRds token) throws Exception {

		Timestamp sysdate = commonSrv.querySysdate();
		long qstId = commonSrvm.querySeqId(Constant.SEQUENCE.S_QUESTION);
		
		QuestionSqs qs = questionSrv.sendQuestion(req, token, qstId, sysdate);
		Question_res res = new Question_res();
		res.setRet(Constant.SUCCESS);
		res.setQuestion_id(qs.getQuestion_id());
		res.setDatetime(qs.getTime());
		return res;
	}

	public void questionValidate(Question_req req, TokenRds token) {
	}
}
