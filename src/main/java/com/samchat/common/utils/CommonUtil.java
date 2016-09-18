package com.samchat.common.utils;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;

import com.samchat.common.enums.Constant;

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

	public static Object methodInvoke(Object obj, String methodStr) throws Exception {
		return methodInvoke(obj, methodStr, new Object[] {}, new Class[] {});
	}

	public static Object methodInvoke(Object obj, String methodStr, Object[] paramObjArr, Class[] paramTypeArr)
			throws Exception {
		Class clazz = obj.getClass();
		log.info("method invoke : class[" + clazz.getSimpleName() + "], method:[" + methodStr + "]");
		Method method = clazz.getDeclaredMethod(methodStr, paramTypeArr);
		return method.invoke(obj, paramObjArr);
	}

	public static Object methodInvoke(Object obj, String methodStr, List<Object> paramObjlist) throws Exception {

		Object[] paramObjArr = paramObjlist.toArray();
		int length = paramObjArr.length;

		Class[] paramTypeArr = new Class[paramObjArr.length];
		for (int i = 0; i < length; i++) {
			paramTypeArr[i] = paramObjArr[i].getClass();
		}
		log.info("method invoke : class[" + obj.getClass().getSimpleName() + "], method:[" + methodStr + "]");
		Method method = obj.getClass().getDeclaredMethod(methodStr, paramTypeArr);

		return method.invoke(obj, paramObjArr);
	}

	public static String encryptStr3Des(String originalPwd) {
		return originalPwd;
	}

	public static String getRandom() {
		return String.valueOf(Math.random()).substring(2);
	}

	public static String getRegisterCode(String countryCode, String phoneNo) {
		return CacheUtil.get(Constant.CACHE_NAME.REGISTER_CODE, countryCode + "_" + phoneNo);
	}

	public static void putRegisterCode(String countryCode, String phoneNo, String registerCode, int timeToIdleSeconds,
			int timeToLiveSeconds) {
		CacheUtil.put(Constant.CACHE_NAME.REGISTER_CODE, countryCode + "_" + phoneNo, registerCode, timeToIdleSeconds,
				timeToLiveSeconds);
	}

	public static String getE164PhoneNo(String countryCode, String phoneNo) {
		return "+" + countryCode + phoneNo;
	}

	public static String getSysConfigStr(String paramCode) {
		return (String) CacheUtil.getSysconfigOnKey(Constant.CACHE_NAME.SYS_CONFIG, paramCode);
	}

	public static int getSysConfigInt(String paramCode) {
		return Integer.parseInt(getSysConfigStr(paramCode));
	}

	public static byte getSysConfigByte(String paramCode) {
		return Byte.parseByte(getSysConfigStr(paramCode));
	}

	public static String getRadom(int len) {
		return (Math.random() + "").substring(2, len + 2);

	}

	public static String getHashSharding(String tableName, long userId) {
		int shardingMode = CommonUtil.getSysConfigInt(("sharding_" + tableName).toLowerCase());
		return tableName + "_" + userId % shardingMode;
	}
}
