package com.samchat.common.listeners;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.thread.SysConfigRefreshThread;
import com.samchat.common.thread.SysMsgTplRefreshThread;
import com.samchat.service.interfaces.ICommonSrvs;

public class SysConfigRefreshListener implements ServletContextListener {

	private final Logger log = Logger.getLogger(SysConfigRefreshListener.class);
	
	@Autowired
	private ICommonSrvs commonSrv;

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public SysConfigRefreshListener() {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			commonSrv = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()).getBean(
					ICommonSrvs.class);
			SysConfigRefreshThread sct = new SysConfigRefreshThread(commonSrv);
			sct.run();
			SysMsgTplRefreshThread  smt = new SysMsgTplRefreshThread(commonSrv);
			smt.run();

			TSysConfigs intervalCfg = commonSrv.querySysconfig("sys_config_refresh_interval");
			int interval = Integer.parseInt(intervalCfg.getParam_value());
			
			ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
			ses.scheduleWithFixedDelay(sct, 0, interval, TimeUnit.MINUTES);
			ses.scheduleWithFixedDelay(smt, 0, interval, TimeUnit.MINUTES);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Error("init sysconfig, error");
		}
	}
}
