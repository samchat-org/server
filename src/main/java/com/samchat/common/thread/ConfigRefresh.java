package com.samchat.common.thread;

import java.util.List;

import net.sf.ehcache.Cache;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.samchat.common.beans.manual.common.SecurityAccessBean;
import com.samchat.common.enums.Constant;
import com.samchat.common.utils.CacheUtil;

public abstract class ConfigRefresh<T> extends Thread{
	
	private final Logger log = Logger.getLogger(ConfigRefresh.class);
	
	public abstract List<T> queryCfgList();
	
	public abstract String getParamCode(T cfg);
	
	public abstract String getParamValue(T cfg);
	
	public abstract String getEcacheVal();
	
	public void run(){
		List<T> cfgs = queryCfgList();
		for (T cfg : cfgs) {
			String paramCode = getParamCode(cfg);
			String paramValue = StringUtils.trimToEmpty(getParamValue(cfg));
			SecurityAccessBean<String> cfgCur = CacheUtil.get(getEcacheVal(), paramCode);

			if (cfgCur == null) {
				SecurityAccessBean<String> sab = new SecurityAccessBean<String>();
				sab.setState(Constant.SYS_UNLOCK);
				sab.setValue(paramValue);
				CacheUtil.put(getEcacheVal(), paramCode, sab);

			} else if (!cfgCur.getValue().equals(paramValue)) {
				Cache cache = CacheUtil.getCache(getEcacheVal());
				try {
					cfgCur.setState(Constant.SYS_LOCK);
					log.info("sleep 5s, old value:" + cfgCur.getValue() + "---" + "new value:" + paramValue);
					Thread.sleep(5000L);
					cache.acquireWriteLockOnKey(paramCode);
					cfgCur.setValue(paramValue);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				} finally {
					cache.releaseWriteLockOnKey(paramCode);
					cfgCur.setState(Constant.SYS_UNLOCK);
				}
			}
		}
	}
}
