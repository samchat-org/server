package com.samchat.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.json.appserver.question.QueryPopularRequest_req;
import com.samchat.common.beans.auto.json.appserver.question.QueryPopularRequest_res;
import com.samchat.common.beans.auto.json.appserver.question.QueryPopularRequest_res.Popular_request;
import com.samchat.common.beans.auto.json.appserver.question.Question_req;
import com.samchat.common.beans.auto.json.appserver.question.Question_res;
import com.samchat.common.beans.manual.db.QryPopularRequests;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;
import com.samchat.common.utils.ShardingUtil;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;

public class QuestionAction extends BaseAction {

	@Autowired
	private IQuestionSrvs questionSrv;
	@Autowired
	private ICommonSrvs commonSrv;

	public Question_res question(Question_req req, TokenMappingRds token) throws Exception {

		Timestamp sysdate = commonSrv.querySysdate();
		QuestionSqs qs = questionSrv.saveAndsendQuestion_master(req, token , sysdate);
		Question_res res = new Question_res();
		
		res.setQuestion_id(ShardingUtil.getMonthShardingId(qs.getShardingFlag(), qs.getQuestion_id()));
		res.setDatetime(qs.getTime());
		return res;
	}

	public void questionValidate(Question_req req, TokenMappingRds token) {
	}

	public QueryPopularRequest_res queryPopularRequest(QueryPopularRequest_req req, TokenMappingRds token) {
		long count = req.getBody().getCount();
		QueryPopularRequest_res res = new QueryPopularRequest_res();
		ArrayList<Popular_request> prl = new ArrayList<Popular_request>();
		List<QryPopularRequests> requests = questionSrv.queryPopularRequests((int)count);
		for(QryPopularRequests po : requests){
			QueryPopularRequest_res.Popular_request pr = new QueryPopularRequest_res.Popular_request();
			pr.setContent(po.getContent());
			prl.add(pr);
		}
		res.setPopular_request(prl);
		return res;
	}
	
	public void queryPopularRequestValidate(QueryPopularRequest_req req, TokenMappingRds token) {
		
 	}
}
