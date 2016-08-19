package com.samchat.common.utils;

import java.lang.reflect.Method;

import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.samchat.common.Constant;

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
 		log.debug("method invoke : class[" + clazz.getSimpleName() + "], method:[" + methodStr + "]");
		objret = method.invoke(obj, type);

		return objret;
	}

	public static String encryptStr3Des(String originalPwd) {
		return originalPwd;
	}

	public static String getRandom() {
		return String.valueOf(Math.random()).substring(2);
	}
	
	public static String getRegisterCode(String countryCode, String phoneNo){
		return CacheUtil.get(Constant.CACHE_NAME.REGISTER_CODE_CACHE, countryCode + "_" + phoneNo);
	}
	
	public static void putRegisterCode(String countryCode, String phoneNo, String registerCode, int timeToIdleSeconds, int timeToLiveSeconds){
		CacheUtil.put(Constant.CACHE_NAME.REGISTER_CODE_CACHE, countryCode + "_" + phoneNo, registerCode, timeToIdleSeconds, timeToLiveSeconds);
	}
	
	public static String getE164PhoneNo(String countryCode, String phoneNo){
		return "+" + countryCode + phoneNo;
	}
	
	public static String getSysConfigStr(String paramCode) {
		return (String) CacheUtil.getSysconfigOnKey(Constant.CACHE_NAME.SYS_CONFIG_CACHE, paramCode);
	}
	
	public static int getSysConfigInt(String paramCode){
		return Integer.parseInt(getSysConfigStr(paramCode));
	}
	
	public static byte getSysConfigByte(String paramCode){
		return Byte.parseByte(getSysConfigStr(paramCode));
	}
	
	public static String getRadom(int len){
		return (Math.random() + "").substring(2, len + 2);
		
	}
}
