package com.samchat.service.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;
import com.samchat.common.beans.auto.json.appserver.question.Question_req;
import com.samchat.common.beans.manual.db.QryPopularRequests;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;

public interface IQuestionSrvs extends IBaseSrvs{

	public TQuestionQuestions saveQuestion(QuestionSqs req);

	public QuestionSqs saveAndsendQuestion_master(Question_req req, TokenMappingRds token, Timestamp sysdate) throws Exception;

	public List<QryPopularRequests> queryPopularRequests(int count);
	
	public boolean updateQuestionSendingState(long qstId, int shardingFlag);
	
	public void updateQuestionState(long qstId, byte state, int shardingFlag);
	
	public void saveQuestionSendLog(long qstId, long userIdPro, byte state, Timestamp time, int shardingFlag, String remark);
}
