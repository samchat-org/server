package com.samchat.processor;

import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.samchat.common.thread.SysConfigRefreshThread;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.SpringUtil;
import com.samchat.processor.QuestionDispatch.Dispatcher;

public class MainProcessor {
	
	private static Logger log = Logger.getLogger(MainProcessor.class);

	private ApplicationContext ctx;

	public MainProcessor() throws Exception {
		ctx = SpringUtil.initContext("config/spring");
		SysConfigRefreshThread refresh = (SysConfigRefreshThread) ctx.getBean("sysConfigRefreshThread");
		refresh.run();
		refresh.start();
	}

	public void process(String processor, String cfgPrefix) {
		Thread thread = (Thread) ctx.getBean(processor);
		int count = CommonUtil.getSysConfigInt(cfgPrefix + "_thread_count");
		
		log.info("count:" + count);
		Executors.newScheduledThreadPool(count).execute(thread);
	}

	public static void main(String args[]) throws Exception {
		log.info("dispatcher start");
		if(args.length != 2){
			throw new Exception("input the params: processer name , config perfix ");
		}
		//"advertisementDispatcher", "aws_sqs_advertisement"
		new  MainProcessor().process(args[0], args[1]);
	}

}
