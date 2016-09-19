package com.samchat.service.interfaces;

import java.sql.Timestamp;

import com.samchat.common.beans.manual.json.redis.UserInfoRds;

public interface IBaseSrvs {
	public void setUserInfoRedis(long userId, UserInfoRds uif);

	public UserInfoRds getUserInfoRedis(long userId);

	public UserInfoRds getUserInfoAndSetRedis(long userId) throws Exception;

	public Timestamp querySysdate();
}
