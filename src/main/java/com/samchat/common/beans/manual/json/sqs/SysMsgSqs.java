package com.samchat.common.beans.manual.json.sqs;

public class SysMsgSqs {
	
	private int msgType;

	private QuestionSqs msgObj;

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public QuestionSqs getMsgObj() {
		return msgObj;
	}

	public void setMsgObj(QuestionSqs msgObj) {
		this.msgObj = msgObj;
	}

	
	
	
}
