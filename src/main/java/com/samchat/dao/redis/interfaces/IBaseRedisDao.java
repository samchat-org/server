package com.samchat.dao.redis.interfaces;

import java.util.List;

public interface IBaseRedisDao<K, V> {

	public void set(final String keyStr, final String valueStr, final long expireSec);
	
	public Boolean setNX(final String keyStr, final String valueStr, final long expireSec);
	
	public Boolean setNX(final String keyStr, final Object valueObj, final long expireSec);

	public String get(final String keyStr);
	
	public <T> T getJsonObj(final String keyStr);

	public void delete(K keyStr);

	public void delete(List<K> keyStr);
}
