package com.samchat.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;
import com.samchat.common.beans.auto.json.appserver.question.Question_req;
import com.samchat.common.beans.manual.db.QryPopularRequests;
import com.samchat.common.beans.manual.json.redis.QuSendCtlRds;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.enums.db.QstDbEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CacheUtil;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.ShardingUtil;
import com.samchat.common.utils.SqsUtil;
import com.samchat.dao.db.interfaces.IQuestionDbDao;
import com.samchat.dao.db.interfaces.IUserDbDao;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IQuestionSrvs;

@Service
public class QuestionSrvs extends BaseSrvs implements IQuestionSrvs {

	private static Logger log = Logger.getLogger(QuestionSrvs.class);

	@Autowired
	private IUserRedisDao userRedisDao;

	@Autowired
	private IQuestionDbDao questionDbDao;
	
	@Autowired
	private IUserDbDao userDbDao;

	public TQuestionQuestions saveQuestion(QuestionSqs req) {
		TQuestionQuestions qq = new TQuestionQuestions();
 		qq.setCreate_date(new Timestamp(req.getTime()));
 		qq.setState_date(qq.getCreate_date());
 		qq.setState(QstDbEnum.ContentState.WAIT.val());
		qq.setOpt_type(Constant.QST_OPT_SEND);
		qq.setContent(req.getQuestion());
		qq.setAddress(req.getAddress());
		qq.setPlace_id(req.getPlace_id());
		qq.setLatitude(req.getLatitude());
		qq.setLongitude(req.getLongitude());
		qq.setUser_id(req.getUser_id());
		qq.setSharding_flag(req.getShardingFlag());
		return questionDbDao.saveQuestion(qq);
	}

	public QuestionSqs saveAndsendQuestion_master(Question_req req, TokenMappingRds token, Timestamp sysdate) throws Exception {

		QuestionSqs sqs = new QuestionSqs();
		Question_req.Body body = req.getBody();
 		UserInfoRds user = hgetUserInfoJsonObj(token.getUserId());
  		questionSendControl(sysdate.getTime(), user.getCountry_code(), user.getPhone_no());
  		
  		int shardingFlag = ShardingUtil.getMonthSharding(sysdate);
  		sqs.setShardingFlag(shardingFlag);
  		
 		sqs.setUser_id(token.getUserId());
 		sqs.setTime(sysdate.getTime());
		sqs.setOpt(Constant.QST_OPT_SEND);
		sqs.setQuestion(body.getQuestion());
		Question_req.Location location = body.getLocation();
		if (location != null) {
			sqs.setAddress(location.getAddress());
			sqs.setPlace_id(location.getPlace_id());
			Question_req.Location_info info = location.getLocation_info();
			if (info != null) {
				sqs.setLatitude(info.getLatitude());
				sqs.setLongitude(info.getLongitude());
			}
		}
		TQuestionQuestions question = saveQuestion(sqs);
		
		sqs.setQuestion_id(question.getQuestion_id());
		
		SqsUtil.pushMessage(sqs, SysParamCodeDbEnum.SQS_QUESTION_URL.getParamCode());

		return sqs;
	}

	private void questionSendControl(long sysdate, String countryCode, String cellphone) throws Exception{
		String key = CacheUtil.getQuestSendCtlCacheKey(countryCode, cellphone);
		QuSendCtlRds ctl = userRedisDao.getJsonObj(key, QuSendCtlRds.class);
		if (ctl == null) {
			ctl = new QuSendCtlRds();
		} else {
			if (ctl.getBlock() == Constant.QUESTION_SEND_BLOCK) {
				int blocktime = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.QUESTION_SEND_BLOCK_TIME.getParamCode());
				if (sysdate - ctl.getLast() < blocktime * 60 * 1000) {
					log.info("sysdate:" + sysdate + "--last:" + ctl.getLast() + "--blocktime:" + blocktime);
					throw new AppException(ResCodeAppEnum.SEND_QUESTION_FREQUENT.getCode());
				} else {
					ctl.setBlock(Constant.QUESTION_SEND_UNBLOCK);
					ctl.setFirst(sysdate);
					ctl.setCount(0);
				}
			}
		}
		if (ctl.getFirst() == 0) {
			ctl.setFirst(sysdate);
		}
		ctl.setLast(sysdate);
		ctl.setCount(ctl.getCount() + 1);
		int limit = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.QUESTION_SEND_LIMIT_TIME.getParamCode());
		int count = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.QUESTION_SEND_LIMIT_COUNT.getParamCode());
		if (ctl.getLast() - ctl.getFirst() < limit * 60 * 1000 && ctl.getCount() > count) {
			ctl.setBlock(Constant.QUESTION_SEND_BLOCK);
		}
		userRedisDao.setJsonObj(key, ctl);
	}
	
	public boolean updateQuestionSendingState(long qstId, int shardingFlag){
		List<Byte> expectState = new ArrayList<Byte>();
		expectState.add(QstDbEnum.ContentState.WAIT.val());
		return questionDbDao.updateQuestionState(qstId, QstDbEnum.ContentState.SENDING.val(), expectState, shardingFlag);
	}
	
	public void updateQuestionState(long qstId, byte state, int shardingFlag){
		questionDbDao.updateQuestionState(qstId, state, shardingFlag);
	}
	
	public void saveQuestionSendLog(long qstId, long userIdPro, byte state, Timestamp time, int shardingFlag, String remark){
		questionDbDao.saveQuestionSendLog(qstId, userIdPro, state, time, shardingFlag, remark);
	}
	
	public List<QryPopularRequests> queryPopularRequests(int count){
		return questionDbDao.queryPopularRequests(count);
	}

}
