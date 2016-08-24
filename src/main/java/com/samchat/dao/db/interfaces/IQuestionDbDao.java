package com.samchat.dao.db.interfaces;

import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;

public interface IQuestionDbDao extends IBaseDbDao {
	public TQuestionQuestions saveQuestion(TQuestionQuestions questionQuestion);
}
