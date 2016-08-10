package com.skyworld.utils;

import java.lang.reflect.Method;

import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.skyworld.common.Constant;

public class CommonUtil {

	private static Logger log = Logger.getLogger(CommonUtil.class);

	public static boolean emailFormatValidate(String email) {
		if (email == null) {
			return false;
		}
		return Constant.EMAIL_PATTERN.matcher(email).matches();
	}

	public static boolean phoneNoFormatValidate(String phoneNo) {
		if (phoneNo == null) {
			return false;
		}
		return Constant.PHONE_PATTERN.matcher(phoneNo).matches();
	}

	public static Object methodInvoke(Object obj, Class clazz, String methodStr) throws Exception {
		return methodInvoke(obj, clazz, methodStr, new Class[] {}, new Object[] {});
	}

	public static Object methodInvoke(Object obj, Class clazz, String methodStr, Class[] typezz, Object[] type)
			throws Exception {
		Object objret = null;
		Method method = clazz.getDeclaredMethod(methodStr, typezz);

		log.info("method invoke error: class[" + clazz + "], method:[" + methodStr + "]");
		objret = method.invoke(obj, type);

		return objret;
	}

	public static String encryptStr3Des(String originalPwd) {
		return originalPwd;
	}

	public static String getRandom() {
		return String.valueOf(Math.random()).substring(2);
	}

	public static String getAddedToken(String phoneNo, long time, String deviceId, int timeToIdleSeconds, int timeToLiveSeconds) throws Exception {
		log.info("phoneNo:" + phoneNo + "----time:" + time + "----deviceId:" + deviceId);
		
		for (int i = 0;; i++) {
			log.debug("get Token:" + i);
			String token = Md5Util.getSign4String(phoneNo + "_" + time + "_" + deviceId + i, "");
			Element element = CacheUtil.putIfAbsent(Constant.CACHE_NAME.TOKEN_CACHE, token, phoneNo + "_" + deviceId, timeToIdleSeconds, timeToLiveSeconds);
			if (element == null) {
				return token;
			}
		}
	}
	
	public static String getRegisterCode(String countryCode, String phoneNo){
		return CacheUtil.get(Constant.CACHE_NAME.REGISTER_CODE_CACHE, countryCode + "_" + phoneNo);
	}
	
	public static void putRegisterCode(String countryCode, String phoneNo, String registerCode, int timeToIdleSeconds, int timeToLiveSeconds){
		CacheUtil.put(Constant.CACHE_NAME.REGISTER_CODE_CACHE, countryCode + "_" + phoneNo, registerCode, timeToIdleSeconds, timeToLiveSeconds);
	}
	
	public static String getSysConfigStr(String paramCode){
		return (String)CacheUtil.getLockOnKey(Constant.CACHE_NAME.SYS_CONFIG_CACHE, paramCode);
	}
	
	public static int getSysConfigInt(String paramCode){
		return Integer.parseInt(getSysConfigStr(paramCode));
	}
}
