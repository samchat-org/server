package com.samchat.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.samchat.common.beans.manual.db.QryPublicQueryVO;
import com.samchat.common.thread.SysConfigRefreshThread;
import com.samchat.common.utils.SpringUtil;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.IOfficialAccountSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;
import com.samchat.service.interfaces.ITestSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class JedisTest {

	private static Logger log = Logger.getLogger(JedisTest.class);

	@Autowired
	IUserRedisDao userRedisDao12;

	@Autowired
	private IQuestionSrvs questionSrv;

	@Autowired
	private IAdvertisementSrvs advertisementSrv;

	@Autowired
	private ITestSrvs testSrvs;
	@Autowired
	private IUsersSrvs usersSrvs;
	@Autowired
	IOfficialAccountSrvs officialAccountSrvs;

	public void test(int count) throws Exception { }

	public static void main(String args[]) throws Exception {
		PredictRequest predictRequest = new PredictRequest()
		   .withMLModelId(mlModelId)
		   .withPredictEndpoint(predictEndpoint)
		   .withRecord(record);
	}
}
