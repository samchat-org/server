package com.samchat.common.utils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.beans.manual.common.SecurityAccessBean;

/**
 * 
 * EhCache缓存工具类
 * 
 * @author xucl
 * @date 2016-8-9
 * 
 */
public class CacheUtil {

	private static Logger log = Logger.getLogger(CacheUtil.class);

	private static final String path = "/ehcache.xml";

	private static SingletonCache singletonCache = new SingletonCache();

	private static CacheManager cacheManager = singletonCache.getCacheManager();

	private static class SingletonCache {

		CacheManager cacheManager;

		Set<String> cacheNames = new HashSet<String>();

		SingletonCache() {
			cacheManager = CacheManager.create(getClass().getResource(path));
			Class clazz = Constant.CACHE_NAME.class;
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				try {
					String cacheName = field.get(clazz) + "";
					cacheManager.addCache(cacheName);
					cacheNames.add(cacheName);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					throw new Error("cache " + field.getName() + ",error");
				}
			}
		}

		public CacheManager getCacheManager() {
			return this.cacheManager;
		}

		public Set<String> getCacheNames() {
			return this.cacheNames;
		}
	}

	/**
	 * 
	 * 获取缓存或者添加缓存
	 * 
	 * @param cacheName
	 *            缓存名称
	 * @return
	 */
	public static Cache getCache(String cacheName) {
		if (!singletonCache.cacheNames.contains(cacheName)) {
			throw new RuntimeException("Constant.class is not contained the cache:" + cacheName);
		}
		return cacheManager.getCache(cacheName);
	}

	public static void put(String cacheName, Object key, Object value) {
		getCache(cacheName).put(new Element(key, value));
	}

	public static void put(String cacheName, Object key, Object value, int timeToIdleSeconds,
			final int timeToLiveSeconds) {
		getCache(cacheName).put(new Element(key, value, timeToIdleSeconds, timeToLiveSeconds));
	}

	public static Element putIfAbsent(String cacheName, String key, String value) {
		return getCache(cacheName).putIfAbsent(new Element(key, value));
	}

	public static Element putIfAbsent(String cacheName, String key, String value, int timeToIdleSeconds,
			final int timeToLiveSeconds) {
		return getCache(cacheName).putIfAbsent(new Element(key, value, timeToIdleSeconds, timeToLiveSeconds));
	}

	public static String getSysconfigOnKey(String cacheName, String key) {

		Cache cache = getCache(cacheName);
		Element el = cache.get(key);

		if (el == null) {
			throw new RuntimeException("getSysconfigOnKey, value is null , cacheName:" + cacheName + "--key:" + key);
		}
		
		byte state = ((SecurityAccessBean) el.getObjectValue()).getState();
		log.debug("getSysconfigOnKey, key:" + key + "---state :" + state);
		
		if (state == Constant.SYS_LOCK) {
			try {
				cache.acquireReadLockOnKey(key);
				el = cache.get(key);
				if (el == null) {
					throw new RuntimeException("getSysconfigOnKey value is null , cacheName:" + cacheName + "--key:"
							+ key);
				}
			} finally {
				cache.releaseReadLockOnKey(key);
			}
		}
		return ((SecurityAccessBean<String>) el.getObjectValue()).getValue();
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(String cacheName, Object key) {

		Element element = getCache(cacheName).get(key);
		return element != null ? (T) element.getObjectValue() : null;
	}

	public static void remove(String cacheName, Object key) {
		getCache(cacheName).remove(key);
	}

	public static void removeAll(String cacheName) {
		getCache(cacheName).removeAll();
	}
	
	public static String getRegiserCodeCacheKey(String countryCode, String cellPhone){
		return Constant.CACHE_NAME.REGISTER_CODE_CACHE + ":" + countryCode + "_" + cellPhone;
	}
	
	public static String getTokenCacheKey(String token){
 		return Constant.CACHE_NAME.TOKEN_CACHE + ":" + token ;
	}
	
	public static String getFindpasswordCacheKey(String countryCode, String cellPhone){
		return Constant.CACHE_NAME.FIND_PASSWORD_CODE_CACHE + ":" + countryCode + "_" + cellPhone;
	}

}
