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

	private <T> void hset(final String key, final String field, final T value, final RedisSerializer<T> serializerRet) {
		getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getStrRedisSerializer();
				connection.hSet(serializer.serialize(key), serializer.serialize(field), serializerRet.serialize(value));
				return true;
			}
		});
	}

	private <T> T hget(final String key, final String field, final RedisSerializer<T> serializerRet) {
		T result = getRedisTemplate().execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getStrRedisSerializer();
				byte[] value = connection.hGet(serializer.serialize(key), serializer.serialize(field));
				if (value == null) {
					return null;
				}
				return serializerRet.deserialize(value);
			}
		});
		return result;
	}

	public String hgetStr(String key, String field) {
		return hget(key, field, getStrRedisSerializer());
	}

	@SuppressWarnings("unchecked")
	public <T> T hgetJsonObj(String key, String field) {
		return (T) hget(key, field, getJackson2JsonRedisSerializer());
	}

	private void set(final byte[] key, final byte[] value, final long expireSec) {
		getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				if (expireSec == 0) {
					connection.set(key, value);
				} else {
					connection.setEx(key, expireSec, value);
				}
				return true;
			}
		});
	}

	private Boolean setNX(final byte[] key, final byte[] value, final long expireSec) {
		Boolean result = getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				Boolean dos = connection.setNX(key, value);
				log.info("dos:" + dos);
				if (dos == null || dos) {
					if(expireSec != 0){
						connection.expire(key, expireSec);
					}
 					return true;
				}
				return false;
			}
		});
		return result;
	}

	private <T> T get(final String keyStr, final RedisSerializer<T> serializerRet) {
		T result = getRedisTemplate().execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = getStrRedisSerializer().serialize(keyStr);
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				return serializerRet.deserialize(value);
			}
		});
		return result;

	}

	public void setStr(final String keyStr, final String valueStr, final long expireSec) {
		RedisSerializer<String> serializer = getStrRedisSerializer();
		byte[] key = serializer.serialize(keyStr);
		byte[] value = serializer.serialize(valueStr);
		set(key, value, expireSec);
	}

	public void setJsonObj(final String keyStr, final Object valueObj, final long expireSec) {
		byte[] key = getStrRedisSerializer().serialize(keyStr);
		byte[] value = getJackson2JsonRedisSerializer().serialize(valueObj);
		set(key, value, expireSec);
	}

	public Boolean setStrNX(final String keyStr, final String valueStr, final long expireSec) {
		RedisSerializer<String> serializer = getStrRedisSerializer();
		byte[] key = serializer.serialize(keyStr);
		byte[] value = serializer.serialize(valueStr);
		return setNX(key, value, expireSec);
	}

	public Boolean setJsonObjNX(final String keyStr, final Object valueObj, final long expireSec) {
		byte[] key = getStrRedisSerializer().serialize(keyStr);
		byte[] value = getJackson2JsonRedisSerializer().serialize(valueObj);
		return setNX(key, value, expireSec);
	}

	public String getStr(final String keyStr) {
		RedisSerializer<String> serializer = getStrRedisSerializer();
		return get(keyStr, serializer);
	}

	@SuppressWarnings("unchecked")
	public <T> T getJsonObj(final String keyStr) {
		RedisSerializer<Object> serializer = getJackson2JsonRedisSerializer();
		return (T) get(keyStr, serializer);
	}

	public void hsetStr(String key, String field, String value) {
		hset(key, field, value, getStrRedisSerializer());
	}

	public void hsetJsonObj(String key, String field, Object value) {
		hset(key, field, value, getJackson2JsonRedisSerializer());
	}

	public void delete(K keyStr) {
		getRedisTemplate().delete(keyStr);
	}

	public void delete(List<K> keyStr) {
		getRedisTemplate().delete(keyStr);
	}

}