package com.samchat.dao.redis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.samchat.common.utils.ThreadLocalUtil;
import com.samchat.dao.redis.interfaces.IBaseRedisDao;

public class BaseRedisDao<K, V> implements IBaseRedisDao<K, V> {

	private static Logger log = Logger.getLogger(BaseRedisDao.class);

	private static final String RET_SUC_STR = "OK";

	private static final long RET_SUC_NUM = 1;

	@Autowired
	private ShardedJedisPool shardedJedisPool;

	@Autowired
	private JedisPool jedisPool;

	public void hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hset(key, field, value);
		} catch (Exception ex) {
			log.error("Jedis hset: key-" + key + "~~field-" + field + "~~value-" + value + "~~" + ex.getMessage(), ex);
			throw ex;
		} finally {
			jedis.close();
		}
	}

	public void hsetJsonObj(String key, String field, Object value) throws Exception {
		hset(key, field, ThreadLocalUtil.getRedisObjectMapper().writeValueAsString(value));
	}

	public void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception ex) {
			log.error("Jedis set: key-" + key + "~~value-" + value + "~~" + ex.getMessage(), ex);
			throw ex;
		} finally {
			jedis.close();
		}
	}

	public void set(String key, String value, int expire) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.setex(key, expire, value);
		} catch (Exception ex) {
			log.error("Jedis set: key-" + key + "~~value-" + value + "~~expire-" + expire + "~~" + ex.getMessage(), ex);
			throw ex;
		} finally {
			jedis.close();
		}
	}

	public boolean setNX(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			long ret = jedis.setnx(key, value);
			if (RET_SUC_NUM == ret) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			log.error("Jedis setNX: key-" + key + "~~value-" + value + "~~" + ex.getMessage(), ex);
			throw ex;
		} finally {
			jedis.close();
		}
	}

	public boolean setNX(String key, String value, int expire) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			log.info("expire:" + expire);
			String ret = jedis.set(key, value, "NX", "EX", expire);
			if (RET_SUC_STR.equals(ret)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			log.error("Jedis setNX: key-" + key + "~~value-" + value + "~~" + ex.getMessage(), ex);
			throw ex;
		} finally {
			jedis.close();
		}
	}

	public void setJsonObj(String key, Object valueObj) throws Exception {
		set(key, ThreadLocalUtil.getRedisObjectMapper().writeValueAsString(valueObj));
	}

	public void setJsonObj(String key, Object valueObj, int expire) throws Exception {
		set(key, ThreadLocalUtil.getRedisObjectMapper().writeValueAsString(valueObj), expire);
	}

	public boolean setJsonObjNX(String key, Object valueObj) throws Exception {
		return setNX(key, ThreadLocalUtil.getRedisObjectMapper().writeValueAsString(valueObj));
	}

	public boolean setJsonObjNX(String key, Object valueObj, int expire) throws Exception {
		return setNX(key, ThreadLocalUtil.getRedisObjectMapper().writeValueAsString(valueObj), expire);
	}

	public String getFromMaster(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} catch (Exception ex) {
			log.error("Jedis getFromMaster: key-" + key + "~~" + ex.getMessage(), ex);
			throw ex;
		} finally {
			jedis.close();
		}
	}

	private String getFromSlave(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			return shardedJedis.get(key);
		} catch (Exception ex) {
			log.error("Jedis getFromSlave: key-" + key + "~~" + ex.getMessage(), ex);
			throw ex;
		} finally {
			shardedJedis.close();
		}
	}

	public String get(String key) {
		return getFromSlave(key);
	}

	@SuppressWarnings("unchecked")
	public <T> T getJsonObj(String key) throws Exception {
		String ret = get(key);
		if(ret == null)
			return null;
		return (T)ThreadLocalUtil.getRedisObjectMapper().readValue(get(key), Object.class);
	}

	public String hgetFromMaster(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hget(key, field);
		} catch (Exception ex) {
			log.error("Jedis hgetFromMaster: key-" + key + "~~field-" + field + "~~" + ex.getMessage(), ex);
			throw ex;
		} finally {
			jedis.close();
		}
	}

	public String hgetFromSlave(String key,  String field) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			return shardedJedis.hget(key, field);
		} catch (Exception ex) {
			log.error("Jedis hgetFromSlave: key-" + key + "~~field-" + field + "~~" + ex.getMessage(), ex);
			throw ex;
		} finally {
			shardedJedis.close();
		}
	}

	public String hget(String key, String field) {
		return hgetFromSlave(key, field);
	}

	@SuppressWarnings("unchecked")
	public <T> T hgetJsonObj(String key, String field) throws Exception {
 		String ret = hget(key, field);
		if(ret == null){
			return null;
		}
		return (T)ThreadLocalUtil.getRedisObjectMapper().readValue(ret, Object.class);
	}

	public void delete(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (Exception ex) {
			log.error("Jedis delete: key-" + key + "-" + ex.getMessage(), ex);
			throw ex;
		} finally {
			jedis.close();
		}
	}

	public void delete(List<String> keyls) {
		String[] keyArr = new String[keyls.size()];
		keyls.toArray(keyArr);
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(keyArr);
		} catch (Exception ex) {
			log.error("Jedis delete: key-" + keyls + "-" + ex.getMessage(), ex);
			throw ex;
		} finally {
			jedis.close();
		}

	}

}