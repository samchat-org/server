package com.samchat.dao.db;

 
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;
import com.samchat.common.beans.auto.db.mapper.TQuestionQuestionsMapper;
import com.samchat.dao.db.interfaces.IQuestionDbDao;

@Repository
public class QuestionDbDao extends BaseDbDao implements IQuestionDbDao {
	
	@Autowired
	private TQuestionQuestionsMapper questionQuestionsMapper;
	
	protected String getNamespace() {
		return "questionSqlMapper";
	}
	
	public TQuestionQuestions saveQuestion(TQuestionQuestions  qst){
   		questionQuestionsMapper.insert(qst);
 		return qst;
	}
}
