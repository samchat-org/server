package com.samchat.dao.db.interfaces;

import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;
import com.samchat.common.beans.manual.db.QryPopularRequests;

public interface IQuestionDbDao extends IBaseDbDao {
	public TQuestionQuestions saveQuestion(TQuestionQuestions questionQuestion);
	public List<QryPopularRequests> queryPopularRequests(int count);
}
