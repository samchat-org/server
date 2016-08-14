package com.samchat.common.listeners;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.samchat.common.Constant;
import com.samchat.common.beans.SecurityAccessBean;
import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.utils.CacheUtil;
import com.samchat.service.interfaces.ICommonSrv;

public class SysConfigRefreshListener implements ServletContextListener {

	private final Logger log = Logger.getLogger(SysConfigRefreshListener.class);

	private ICommonSrv commonSrv;

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			commonSrv = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()).getBean(
					ICommonSrv.class);
			refresh();

			TSysConfigs intervalCfg = commonSrv.querySysconfig("sys_config_refresh_interval");
			int interval = Integer.parseInt(intervalCfg.getParam_value());

			Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Thread() {
				public void run() {
					refresh();
				}
			}, 0, interval, TimeUnit.MINUTES);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Error("init sysconfig, error");
		}
	}

	private void refresh() {

		List<TSysConfigs> cfgs = commonSrv.queryAllSysconfigs();

		for (TSysConfigs cfg : cfgs) {
			String paramCode = cfg.getParam_code();
			String paramValue = cfg.getParam_value();
			SecurityAccessBean<String> cfgCur = CacheUtil.get(Constant.CACHE_NAME.SYS_CONFIG_CACHE, paramCode);

			if (cfgCur == null) {
				SecurityAccessBean sab = new SecurityAccessBean();
				sab.setState(Constant.SYS_UNLOCK);
				sab.setValue(paramValue);
				CacheUtil.put(Constant.CACHE_NAME.SYS_CONFIG_CACHE, paramCode, sab);

			} else if (!cfgCur.getValue().equals(paramValue)) {
				Cache cache = CacheUtil.getCache(Constant.CACHE_NAME.SYS_CONFIG_CACHE);
				try {
					cfgCur.setState(Constant.SYS_LOCK);
					log.error("update cfg value, sleep 5s");
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
