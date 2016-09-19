package com.samchat.service.interfaces;

import java.sql.Timestamp;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.manual.json.redis.UserInfoProRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.utils.CacheUtil;
import com.samchat.dao.db.interfaces.ICommonDbDao;
import com.samchat.dao.db.interfaces.IUserDbDao;
import com.samchat.dao.redis.interfaces.IUserRedisDao;

public class BaseSrvs implements IBaseSrvs {

	@Autowired
	protected IUserRedisDao<String, Object> userRedisDao;

	@Autowired
	protected ICommonDbDao commonDbDao;

	@Autowired
	private IUserDbDao userDbDao;

	public void setUserInfoRedis(long userId, UserInfoRds uif) {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		userRedisDao.set(key, uif, 0);
	}

	public UserInfoRds getUserInfoRedis(long userId) {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		return (UserInfoRds) userRedisDao.getJsonObj(key);
	}

	public UserInfoRds getUserInfoAndSetRedis(long userId) throws Exception {
		UserInfoRds uur = getUserInfoRedis(userId);
		if (uur == null) {
			TUserUsers uu = userDbDao.queryUser(userId);
			if (uu == null) {
				return null;
			} else {
				uur = new UserInfoRds();
				PropertyUtils.copyProperties(uur, uu);
				TUserProUsers uup = userDbDao.queryProUser(userId);
				if (uup != null) {
					UserInfoProRds uupr = new UserInfoProRds();
					uur.setUserInfoProRds(uupr);
					PropertyUtils.copyProperties(uupr, uup);
				}
				setUserInfoRedis(userId, uur);
			}
		}
		return uur;
	}

	public Timestamp querySysdate() {
		return commonDbDao.querySysdate();
	}
}
