package com.samchat.dao.redis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.samchat.dao.redis.interfaces.IUserRedisDao;

@Repository
public class UserRedisDao extends BaseRedisDao<String, Object> implements IUserRedisDao<String, Object> {

	private static Logger log = Logger.getLogger(UserRedisDao.class);

}