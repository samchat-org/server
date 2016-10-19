package com.samchat.service;

import java.sql.Timestamp;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.manual.common.SysdateObjBean;
import com.samchat.common.beans.manual.json.redis.TokenValRds;
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
	
	public void hsetUserInfoJsonObjRedis(long userId, String filed, Object uif) throws Exception {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		userRedisDao.hsetJsonObj(key, filed, uif);
	}
	
	public void hsetUserInfoStrRedis(long userId, String filed, String uif) throws Exception {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		userRedisDao.hset(key, filed, uif);
	}
	
	public void hsetUserInfoClientId(long userId, String clientId) {
		try {
			hsetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.CLIENT_ID.val(), clientId);
		} catch (Exception e) {
			
		}
	}
	
	public void hsetUserInfoCustomerListDate(long userId, String date) {
		try {
			hsetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.CUSTOMER_LIST_DATE.val(), date);
		} catch (Exception e) {
			
		}
	}
	
	public void hsetUserInfoServicerListDate(long userId, String date) {
		try {
			hsetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.SERVICER_LIST_DATE.val(), date);
		} catch (Exception e) {
			
		}
	}
	
	public void hsetUserInfoFollowListDate(long userId, String date) {
		try {
			hsetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.FOLLOW_LIST_DATE.val(), date);
		} catch (Exception e) {
			
		}
	}

	public void hsetUserInfoJsonObj(long userId, UserInfoRds uur) {
		try {
			hsetUserInfoJsonObjRedis(userId, UserInfoFieldRdsEnum.USER_INFO.val(), uur);
		} catch (Exception e) {
		}
	}
	
	public void hsetUserInfoProsJsonObj(long userId, UserInfoProRds uur) {
		try {
			hsetUserInfoJsonObjRedis(userId, UserInfoFieldRdsEnum.PROS_INFO.val(), uur);
		} catch (Exception e) {
		}
	}

	public void hsetUserInfoTokenJsonObj(long userId, TokenValRds tvr) {
		try {
			hsetUserInfoJsonObjRedis(userId, UserInfoFieldRdsEnum.TOKEN.val(), tvr);
		} catch (Exception e) {
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T hgetUserInfoJsonObjRedis(long userId, String field) throws Exception {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		return (T) userRedisDao.hgetJsonObj(key, field);
	}
	
	public String hgetUserInfoStrRedis(long userId, String field) throws Exception {
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		return userRedisDao.hget(key, field);
	}
	
 	public String hgetUserInfoClientId(long userId) {
		String t = null;
		try {
			t = hgetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.CLIENT_ID.val());
		} catch (Exception e) {
		}
		return t;
	}
 	
 	public String hgetUserInfoCustomerListDate(long userId) {
		String t = null;
		try {
			t = hgetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.CUSTOMER_LIST_DATE.val());
		} catch (Exception e) {
		}
		return t;
	}
 	
 	public String hgetUserInfoServicerListDate(long userId) {
		String t = null;
		try {
			t = hgetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.SERVICER_LIST_DATE.val());
		} catch (Exception e) {
		}
		return t;
	}
 	
 	public String hgetUserInfoFollowListDate(long userId) {
		String t = null;
		try {
			t = hgetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.FOLLOW_LIST_DATE.val());
		} catch (Exception e) {
		}
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public UserInfoRds hgetUserInfoJsonObj(long userId){
		UserInfoRds t = null;
		try {
			t = hgetUserInfoJsonObjRedis(userId, UserInfoFieldRdsEnum.USER_INFO.val());
		} catch (Exception e) {
		}
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public UserInfoProRds hgetUserInfoProsJsonObj(long userId){
		UserInfoProRds t = null;
		try {
			t = hgetUserInfoJsonObjRedis(userId, UserInfoFieldRdsEnum.PROS_INFO.val());
		} catch (Exception e) {
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public TokenValRds hgetUserInfoTokenJsonObj(long userId){
		TokenValRds t = null;
		try {
			t = hgetUserInfoJsonObjRedis(userId, UserInfoFieldRdsEnum.TOKEN.val());
		} catch (Exception e) {
		}
		return t;
	}

	public Timestamp querySysdate() {
		return commonDbDao.querySysdate();
	}

	public SysdateObjBean querySysdateObj() throws Exception {
		return commonDbDao.querySysdateObj();
	}

}
