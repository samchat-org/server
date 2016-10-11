package com.samchat.service;

import java.sql.Timestamp;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.action.UserAction;
import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.manual.json.redis.UserInfoProRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.enums.cache.UserInfoFieldRdsEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CacheUtil;
import com.samchat.dao.db.interfaces.ICommonDbDao;
import com.samchat.dao.db.interfaces.IUserDbDao;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IBaseSrvs;

public class BaseSrvs implements IBaseSrvs {
	
	private static Logger log = Logger.getLogger(BaseSrvs.class);

	@Autowired
	protected IUserRedisDao<String, Object> userRedisDao;

	@Autowired
	protected ICommonDbDao commonDbDao;

	@Autowired
	private IUserDbDao userDbDao;

	public void hsetUserInfoJsonObjRedis(long userId, String filed, Object uif) {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		userRedisDao.hsetJsonObj(key, filed, uif);
	}

	public void hsetUserInfoStrRedis(long userId, String field, String value) {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		log.info("key:" + key + "--field:" + field + "--value:" + value);
		userRedisDao.hsetStr(key, field, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T hgetUserInfoJsonObjRedis(long userId, String field) {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		return (T) userRedisDao.hgetJsonObj(key, field);
	}

	public String hgetUserInfoStrRedis(long userId, String field) {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		return userRedisDao.hgetStr(key, field);
	}

	public UserInfoRds getUserInfoAndSetRedis(long userId) throws Exception {
		UserInfoRds uur = hgetUserInfoJsonObjRedis(userId, UserInfoFieldRdsEnum.BASE_INFO.val());
		if (uur == null) {
			TUserUsers uu = userDbDao.queryUser(userId);
			if (uu == null) {
				throw new AppException(ResCodeAppEnum.USER_NOT_EXIST.getCode());
 			} else {
				uur = new UserInfoRds();
				PropertyUtils.copyProperties(uur, uu);
				TUserProUsers uup = userDbDao.queryProUser(userId);
				if (uup != null) {
					UserInfoProRds uupr = new UserInfoProRds();
					uur.setUserInfoProRds(uupr);
					PropertyUtils.copyProperties(uupr, uup);
				}
 				hsetUserInfoJsonObjRedis(userId, UserInfoFieldRdsEnum.BASE_INFO.val(), uur);
			}
		}
		return uur;
	}

	public Timestamp querySysdate() {
		return commonDbDao.querySysdate();
	}
	
}
