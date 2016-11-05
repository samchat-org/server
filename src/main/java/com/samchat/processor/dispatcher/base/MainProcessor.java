package com.samchat.processor.dispatcher.base;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.samchat.common.thread.SysConfigRefreshThread;
import com.samchat.common.utils.SpringUtil;

public class MainProcessor {
	
	private static Logger log = Logger.getLogger(MainProcessor.class);

	private ApplicationContext ctx;

	public MainProcessor() throws Exception {
		ctx = SpringUtil.initContext("config/spring");
		SysConfigRefreshThread refresh = (SysConfigRefreshThread) ctx.getBean("sysConfigRefreshThread");
		refresh.run();
		refresh.start();
	}

	public void process(String processor) {
		DispatcherBase dispatcher = (DispatcherBase) ctx.getBean(processor);
		dispatcher.multipleThreadsRun();
	}

	public static void main(String args[]) throws Exception {
		log.info("dispatcher start");
		if(args.length != 1){
			throw new Exception("input the params: processer name");
		}
		new MainProcessor().process(args[0]);
	}

}
