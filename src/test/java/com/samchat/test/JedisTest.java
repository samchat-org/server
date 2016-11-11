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

	public void test(int count) throws Exception {
//		GooglePlaceUtil.autocomplete("abcd");
 		List<QryPublicQueryVO> pqs = officialAccountSrvs.queryPublicList("s", count);
		for(QryPublicQueryVO vo : pqs){
			log.info("user_id:" + vo.getUser_id());
		}
	}

	public static void main(String args[]) throws Exception {
		ApplicationContext ctx = SpringUtil.initContext("config/spring");
		JedisTest j = (JedisTest)ctx.getBean("jedisTest");
		SysConfigRefreshThread refresh = (SysConfigRefreshThread) ctx.getBean("sysConfigRefreshThread");
		refresh.run();
		refresh.start();
		j.test(0);
		j.test(5);
		j.test(10);
	}
}
