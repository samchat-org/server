package com.samchat.processor.dispatcher;

import org.apache.log4j.Logger;

import com.amazonaws.services.sqs.model.Message;
import com.samchat.processor.dispatcher.base.DispatcherBase;

public class SystemMsgDispatcher extends DispatcherBase {
	
	private static Logger log = Logger.getLogger(SystemMsgDispatcher.class);

	protected void process(Message message) throws Exception {

	}
}
