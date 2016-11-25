package com.samchat.dao.db.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;
import com.samchat.common.beans.manual.db.QryPopularRequests;

public interface IQuestionDbDao extends IBaseDbDao {
	public TQuestionQuestions saveQuestion(TQuestionQuestions questionQuestion);
	public List<QryPopularRequests> queryPopularRequests(int count);
	public boolean updateQuestionState(long qstId, byte destState, List<Byte> expectState, int shardingFlag);
	public void updateQuestionState(long qstId, byte destState, int shardingFlag);
	public void saveQuestionSendLog(long qstId, long userIdPro, byte state, Timestamp time, int shardingFlag, String remark);
}
