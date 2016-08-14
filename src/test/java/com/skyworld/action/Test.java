package com.samchat.action;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.samchat.common.Constant;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CacheUtil;

public class Test {
	public static void main(String[] args) throws Exception{
		Cache cache = CacheUtil.getCache(Constant.CACHE_NAME.SYS_CONFIG_CACHE);
		AppException ex = new AppException(123);
		cache.put(new Element("1", ex));
		ex.setErrorCode(234);
		System.out.print(((AppException)cache.get("1").getObjectValue()).getErrorCode());
	}
}
