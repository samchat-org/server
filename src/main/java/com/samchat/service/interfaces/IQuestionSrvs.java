package com.samchat.service.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;
import com.samchat.common.beans.auto.json.appserver.question.Question_req;
import com.samchat.common.beans.manual.db.QryPopularRequests;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;

public interface IQuestionSrvs extends IBaseSrvs{

	public TQuestionQuestions saveQuestion(QuestionSqs req);

	public QuestionSqs sendQuestion(Question_req req, TokenRds token, long qstId, Timestamp sysdate) throws Exception;

	public List<QryPopularRequests> queryPopularRequests(int count);
}
