package com.samchat.common.listeners;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.beans.manual.common.SecurityAccessBean;
import com.samchat.common.thread.SysConfigRefreshThread;
import com.samchat.common.utils.CacheUtil;
import com.samchat.service.interfaces.ICommonSrv;

public class SysConfigRefreshListener implements ServletContextListener {

	private final Logger log = Logger.getLogger(SysConfigRefreshListener.class);
	
	@Autowired
	private ICommonSrv commonSrv;

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public SysConfigRefreshListener() {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			commonSrv = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()).getBean(
					ICommonSrv.class);
			SysConfigRefreshThread thread = new SysConfigRefreshThread(commonSrv);
			thread.run();

			TSysConfigs intervalCfg = commonSrv.querySysconfig("sys_config_refresh_interval");
			int interval = Integer.parseInt(intervalCfg.getParam_value());

			Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(thread, 0, interval, TimeUnit.MINUTES);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Error("init sysconfig, error");
		}
	}
}
