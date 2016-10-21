package com.samchat.common.datas;

import org.apache.log4j.Logger;

import com.samchat.test.JedisTest;

public class DbContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	private static Logger log = Logger.getLogger(DbContextHolder.class);
	public static void setDbType(String dbType) {
 		contextHolder.set(dbType);
	}

	public static String getDbType() {
		return ((String) contextHolder.get());
	}

	public static void clearDbType() {
		contextHolder.remove();
	}
}
