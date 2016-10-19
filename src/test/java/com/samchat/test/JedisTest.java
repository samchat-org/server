package com.samchat.test;

import java.sql.Timestamp;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.IQuestionSrvs;
import com.samchat.service.interfaces.ITestSrvs;

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

	public void test() throws Exception {
		testSrvs.insertDict();
		testSrvs.queryDict();
	}

	public static void main(String args[]) throws Exception {
		UserInfoRds uur = new UserInfoRds();
		uur.setNowVersion(123123);
		TUserUsers user = new TUserUsers();
		user.setAddress("123123");
		PropertyUtils.copyProperties(uur, user);
	}
}
