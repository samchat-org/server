package com.samchat.service.interfaces;

import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;
import com.samchat.common.beans.auto.json.appserver.question.Question_req;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;

public interface IQuestionSrv {

	public TQuestionQuestions saveQuestion(QuestionSqs req);

	public QuestionSqs sendQuestion(Question_req req, TokenRds token) throws Exception;

 }
