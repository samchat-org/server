package com.samchat.dao.redis.interfaces;

import java.util.List;

public interface IBaseRedisDao<K, V> {

	public String hgetStr(String key, String field);

	@SuppressWarnings("unchecked")
	public <T> T hgetJsonObj(String key, String field);

	public void setStr(final String keyStr, final String valueStr, final long expireSec);

	public void setJsonObj(final String keyStr, final Object valueObj, final long expireSec);

	public Boolean setStrNX(final String keyStr, final String valueStr, final long expireSec);

	public Boolean setJsonObjNX(final String keyStr, final Object valueObj, final long expireSec);

	public String getStr(final String keyStr);

	@SuppressWarnings("unchecked")
	public <T> T getJsonObj(final String keyStr);

	public void hsetStr(String key, String field, String value);

	public void hsetJsonObj(String key, String field, Object value);

	public void delete(K keyStr);

	public void delete(List<K> keyStr);

}
