package com.samchat.dao.redis;

import com.samchat.dao.redis.interfaces.ICommonRedisDao;

public class CommonRedisDao  extends BaseRedisDao implements ICommonRedisDao{
	
	public void setQuestionRecallFlag(String bussinessId, String flag){
		set(bussinessId, flag);
	}

}
