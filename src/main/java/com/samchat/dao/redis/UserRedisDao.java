package com.samchat.dao.redis;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.samchat.dao.redis.interfaces.IUserRedisDao;

@Repository
public class UserRedisDao extends BaseRedisDao implements IUserRedisDao{

	private static Logger log = Logger.getLogger(UserRedisDao.class);

}