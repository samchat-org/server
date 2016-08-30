package com.samchat.dao.redis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.samchat.dao.redis.interfaces.IBaseRedisDao;

public class BaseRedisDao<K, V> implements IBaseRedisDao<K, V> {

	private static Logger log = Logger.getLogger(BaseRedisDao.class);

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer;

	protected RedisSerializer<String> getStrRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}

	protected RedisSerializer<Object> getJackson2JsonRedisSerializer() {
		return this.jackson2JsonRedisSerializer;
	}

	protected RedisTemplate<K, V> getRedisTemplate() {
		return this.redisTemplate;
	}

	public void set(final String keyStr, final String valueStr, final long expireSec) {
		getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getStrRedisSerializer();
				byte[] key = serializer.serialize(keyStr);
				byte[] value = serializer.serialize(valueStr);
				log.info("keystr:" + keyStr + "--valueStr:" + valueStr + "--expireSec:" + expireSec);
				if (expireSec == 0) {
					connection.set(key, value);
				} else {
					connection.setEx(key, expireSec, value);
				}
				return true;
			}
		});
	}

	public void set(final String keyStr, final Object valueObj, final long expireSec) {
		getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = getStrRedisSerializer().serialize(keyStr);
				byte[] value = getJackson2JsonRedisSerializer().serialize(valueObj);
				if (expireSec == 0) {
					connection.set(key, value);
				} else {
					connection.setEx(key, expireSec, value);
				}
				return true;
			}
		});
	}

	public Boolean setNX(final String keyStr, final String valueStr, final long expireSec) {
		Boolean result = getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getStrRedisSerializer();
				byte[] key = serializer.serialize(keyStr);
				byte[] value = serializer.serialize(valueStr);
				log.info("keystr:" + keyStr + "--valueStr:" + valueStr + "--expireSec:" + expireSec);
				Boolean dos = connection.setNX(key, value);
				log.info("dos:" + dos);
				if (dos == null || dos) {
					connection.expire(key, expireSec);
					return true;
				}
				return false;
			}
		});
		return result;
	}

	public Boolean setNX(final String keyStr, final Object valueObj, final long expireSec) {

		Boolean result = getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

				byte[] key = getStrRedisSerializer().serialize(keyStr);
				byte[] value = getJackson2JsonRedisSerializer().serialize(valueObj);
				Boolean dos = connection.setNX(key, value);
				log.info("dos:" + dos);
				if (dos == null || dos) {
					if (expireSec != 0) {
						connection.expire(key, expireSec);
					}
					return true;
				}
				return false;
			}
		});
		return result;
	}

	public String get(final String keyStr) {
		String result = getRedisTemplate().execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getStrRedisSerializer();
				byte[] key = serializer.serialize(keyStr);
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				return serializer.deserialize(value);
			}
		});
		return result;
	}

	public <T> T getJsonObj(final String keyStr) {
		T result = getRedisTemplate().execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = getStrRedisSerializer().serialize(keyStr);
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				return (T) getJackson2JsonRedisSerializer().deserialize(value);
			}
		});
		return result;

	}

	public void delete(K keyStr) {
		getRedisTemplate().delete(keyStr);
	}

	public void delete(List<K> keyStr) {
		getRedisTemplate().delete(keyStr);
	}
	
}