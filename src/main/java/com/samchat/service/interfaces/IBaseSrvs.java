package com.samchat.service.interfaces;

import java.sql.Timestamp;

import org.apache.commons.beanutils.PropertyUtils;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.manual.json.redis.UserInfoProRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.utils.CacheUtil;

public interface IBaseSrvs {

	public void hsetUserInfoJsonObjRedis(long userId, String filed, Object uif);

	public void hsetUserInfoStrRedis(long userId, String field, String value);

	@SuppressWarnings("unchecked")
	public <T> T hgetUserInfoJsonObjRedis(long userId, String field);

	public String hgetUserInfoStrRedis(long userId, String field);

	public UserInfoRds getUserInfoAndSetRedis(long userId) throws Exception;

	public Timestamp querySysdate();
}
