package com.samchat.dao.db;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;
import com.samchat.common.beans.auto.db.mapper.TQuestionPopularRequestsMapper;
import com.samchat.common.beans.auto.db.mapper.TQuestionQuestionsMapper;
import com.samchat.common.beans.manual.db.QryPopularRequests;
import com.samchat.dao.db.interfaces.IQuestionDbDao;

@Repository
public class QuestionDbDao extends BaseDbDao implements IQuestionDbDao {
	
	@Autowired
	private TQuestionQuestionsMapper questionQuestionsMapper;
	
	@Autowired
	private TQuestionPopularRequestsMapper questionPopularRequestsMapper;
	
	protected String getNamespace() {
		return "questionSqlMapper";
	}
	
	public TQuestionQuestions saveQuestion(TQuestionQuestions  qst){
   		questionQuestionsMapper.insert(qst);
 		return qst;
	}
	
	public List<QryPopularRequests> queryPopularRequests(int count){
		PageInfo<QryPopularRequests> info = executePageSql("queryPopularRequests", null , 1, count);
		return info.getList();
	}
}
