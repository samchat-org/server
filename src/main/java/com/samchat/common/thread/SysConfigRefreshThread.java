package com.samchat.common.thread;

import java.util.List;

import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.beans.manual.common.SecurityAccessBean;
import com.samchat.common.utils.CacheUtil;
import com.samchat.service.interfaces.ICommonSrv;

public class SysConfigRefreshThread extends Thread {

	private final Logger log = Logger.getLogger(SysConfigRefreshThread.class);

	@Autowired
	private ICommonSrv commonSrv;
	
	public SysConfigRefreshThread(){
		
	}
	
	public SysConfigRefreshThread(ICommonSrv commonSrv){
		this.commonSrv = commonSrv;
	}
	
	public void run() {

		List<TSysConfigs> cfgs = commonSrv.queryAllSysconfigs();

		for (TSysConfigs cfg : cfgs) {
			String paramCode = cfg.getParam_code();
			String paramValue = cfg.getParam_value();
			SecurityAccessBean<String> cfgCur = CacheUtil.get(Constant.CACHE_NAME.SYS_CONFIG, paramCode);

			if (cfgCur == null) {
				SecurityAccessBean<String> sab = new SecurityAccessBean<String>();
				sab.setState(Constant.SYS_UNLOCK);
				sab.setValue(paramValue);
				CacheUtil.put(Constant.CACHE_NAME.SYS_CONFIG, paramCode, sab);

			} else if (!cfgCur.getValue().equals(paramValue)) {
				Cache cache = CacheUtil.getCache(Constant.CACHE_NAME.SYS_CONFIG);
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
					cfg.setState(Constant.SYS_UNLOCK);
				}
			}
		}
	}
}
