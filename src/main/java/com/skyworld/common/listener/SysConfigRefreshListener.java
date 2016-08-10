package com.skyworld.common.listener;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.skyworld.beans.auto.db.entitybeans.TSysConfigs;
import com.skyworld.common.Constant;
import com.skyworld.service.interfaces.ICommonSrv;
import com.skyworld.utils.CacheUtil;

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
			throw new Error("init sysconfig, error");
		}
	}

	private void refresh() {
		List<TSysConfigs> cfgs = commonSrv.queryAllSysconfigs();

		for (TSysConfigs cfg : cfgs) {
			String paramCode = cfg.getParam_code();
			String paramValue = cfg.getParam_value();
			String value = CacheUtil.get(Constant.CACHE_NAME.SYS_CONFIG_CACHE, paramCode);

			if (value == null || !value.equals(paramValue)) {
				CacheUtil.putLockOnKey(Constant.CACHE_NAME.SYS_CONFIG_CACHE, paramCode, paramValue);
			}
		}
	}
}
