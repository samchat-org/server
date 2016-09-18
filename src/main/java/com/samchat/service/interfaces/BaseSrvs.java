package com.samchat.service.interfaces;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.utils.CacheUtil;
import com.samchat.dao.db.interfaces.ICommonDbDao;
import com.samchat.dao.redis.interfaces.IUserRedisDao;

public class BaseSrvs implements IBaseSrvs{
	
	@Autowired
	protected IUserRedisDao<String, Object> userRedisDao;
	
	@Autowired
	protected ICommonDbDao commonDbDao;
	
	public void setUserInfoRedis(long userId, UserInfoRds uif) {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		userRedisDao.set(key, uif, 0);
	}

	public UserInfoRds getUserInfoRedis(long userId) {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		return (UserInfoRds) userRedisDao.getJsonObj(key);
	}

	public Timestamp querySysdate() {
		return commonDbDao.querySysdate();
 	}
}
