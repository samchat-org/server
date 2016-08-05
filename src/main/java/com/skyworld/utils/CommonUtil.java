package com.skyworld.utils;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class CommonUtil {
	
	private static Logger log = Logger.getLogger(CommonUtil.class);

	public static Object methodInvoke(Object obj, Class clazz, String methodStr) throws Exception{
 		return methodInvoke(obj, clazz, methodStr, new Class[] {}, new Object[] {});
 	}
	
	public static Object methodInvoke(Object obj, Class clazz, String methodStr, Class[] typezz, Object[] type) throws Exception{
		Object objret = null;
 		try {
  			Method method = clazz.getDeclaredMethod(methodStr, typezz);
 			objret = method.invoke(obj, type);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
 			throw new Exception("method invoke error: class[" + clazz + "], method:[" + methodStr + "]");
		}
 		return objret;
	}
}
