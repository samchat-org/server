package com.samchat.common.utils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.samchat.common.beans.manual.common.SecurityAccessBean;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.cache.CacheNameCacheEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;

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
			CacheNameCacheEnum[] cns = CacheNameCacheEnum.values();
			for (CacheNameCacheEnum cn : cns) {
				try {
					if (cn.name().startsWith("ECH")) {
						cacheManager.addCache(cn.val());
						cacheNames.add(cn.val());
					}
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					throw new Error("cache " + cn.name() + ",error");
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

	@SuppressWarnings("unchecked")
	public static String getSysconfigOnKey(String cacheName, String key) {

		Cache cache = getCache(cacheName);
		Element el = cache.get(key);

		if (el == null) {
			throw new RuntimeException("getSysconfigOnKey, value is null , cacheName:" + cacheName + "--key:" + key);
		}

		byte state = ((SecurityAccessBean<String>) el.getObjectValue()).getState();
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

	public static String getSystemId() {
		int systemId = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.SYSTEM_ID.getParamCode());
		if (systemId == 0) {
			return "";
		}
		return systemId + ":";
	}

	public static String getRegiserCodeCacheKey(String countryCode, String cellPhone) {
		return getSystemId() + CacheNameCacheEnum.RDS_REGISTER_CODE.val() + ":" + countryCode + "_" + cellPhone;
	}
	
	public static String getRegiserCodeCtrlCacheKey(String countryCode, String cellPhone) {
		return getSystemId() + CacheNameCacheEnum.RDS_REGISTER_CODE_CTRL.val() + ":" + countryCode + "_" + cellPhone;
	}


	public static String getFindpasswordCacheKey(String countryCode, String cellPhone) {
		return getSystemId() + CacheNameCacheEnum.RDS_FIND_PASSWORD_CODE.val() + ":" + countryCode + "_" + cellPhone;
	}
	
	public static String getFindpasswordCtrlCacheKey(String countryCode, String cellPhone) {
		return getSystemId() + CacheNameCacheEnum.RDS_FIND_PASSWORD_CODE_CTRL.val() + ":" + countryCode + "_" + cellPhone;
	}
	

	public static String getTokenCacheKey(String token) {
		return getSystemId() + CacheNameCacheEnum.RDS_TOKEN.val() + ":" + token;
	}

	public static String getUserInfoCacheKey(String countryCode, String cellPhone) {
		return getSystemId() + CacheNameCacheEnum.RDS_USER_INFO.val() + ":" + countryCode + "_" + cellPhone;
	}
	
	public static String getEditCellPhoneCacheKey(String countryCode, String cellPhone) {
		return getSystemId() + CacheNameCacheEnum.EDIT_CELL_PHONE.val() + ":" + countryCode + "_" + cellPhone;
	}
	
	public static String getEditCellPhoneCtrlCacheKey(String countryCode, String cellPhone) {
		return getSystemId() + CacheNameCacheEnum.EDIT_CELL_PHONE_CTRL.val() + ":" + countryCode + "_" + cellPhone;
	}

	public static String getUserInfoIdCacheKey(long userId) {
		return getSystemId() + CacheNameCacheEnum.RDS_USER_INFO_ID.val() + ":" + userId;

	}

	public static String getQuestSendCtlCacheKey(String countryCode, String cellPhone) {
		return CacheNameCacheEnum.RDS_QUESTION_SEND_CONTROL.val() + ":" + countryCode + "_" + cellPhone;

	}

	public static String getRealToken(String retToken, String deviceId) {
		return retToken + deviceId;
	}

	public static String getToken(String countryCode, String phoneNo, long nowVersion, String deviceId)
			throws Exception {
		return Md5Util.getSign4String(countryCode + "_" + phoneNo + "_" + nowVersion + "_" + deviceId);
	}

}
