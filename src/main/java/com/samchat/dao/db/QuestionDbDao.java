package com.samchat.dao.db;

 
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;
import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestionsExample;
import com.samchat.common.beans.auto.db.entitybeans.TQuestionSendLog;
import com.samchat.common.beans.auto.db.mapper.TQuestionPopularRequestsMapper;
import com.samchat.common.beans.auto.db.mapper.TQuestionQuestionsMapper;
import com.samchat.common.beans.auto.db.mapper.TQuestionSendLogMapper;
import com.samchat.common.beans.manual.db.QryPopularRequests;
import com.samchat.dao.db.interfaces.IQuestionDbDao;

@Repository
public class QuestionDbDao extends BaseDbDao implements IQuestionDbDao {
	
	private static Logger log = Logger.getLogger(QuestionDbDao.class);
	
	@Autowired
	private TQuestionQuestionsMapper questionQuestionsMapper;
	
	@Autowired
	private TQuestionSendLogMapper questionSendLogMapper;
	
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
	
	public boolean updateQuestionState(long qstId, byte destState, List<Byte> expectState, int shardingFlag){

		TQuestionQuestionsExample ace = new TQuestionQuestionsExample();
		log.info("qstId:" + qstId + "--shardingFlag:" + shardingFlag);
		ace.createCriteria().andQuestion_idEqualTo(qstId).andStateIn(expectState).andSharding_flagEqualTo(shardingFlag);
		
		TQuestionQuestions tqq = new TQuestionQuestions();
		tqq.setState(destState);
		int ret = questionQuestionsMapper.updateByExampleSelective(tqq, ace);
		return ret > 0;
	}
	
	public void updateQuestionState(long qstId, byte destState, int shardingFlag){
		TQuestionQuestionsExample ace = new TQuestionQuestionsExample();
		log.info("qstId:" + qstId + "--shardingFlag:" + shardingFlag);
		ace.createCriteria().andQuestion_idEqualTo(qstId).andSharding_flagEqualTo(shardingFlag);
		
		TQuestionQuestions tqq = new TQuestionQuestions();
		tqq.setState(destState);
		questionQuestionsMapper.updateByExampleSelective(tqq, ace);
 		
	}
	
	public void saveQuestionSendLog(long qstId, long userIdPro, byte state, Timestamp time, int shardingFlag, String remark){
		TQuestionSendLog qst = new TQuestionSendLog();
		qst.setQst_id(qstId);
		qst.setUser_id_pro(userIdPro);
		qst.setState(state);
		qst.setState_date(time);
		qst.setCreate_date(time);
		qst.setSharding_flag(shardingFlag);
		qst.setRemark(remark);
		questionSendLogMapper.insert(qst);
	}
}
