package com.samchat.common.beans.auto.json.ni.msg;

public class SendBatchMsgFieldBody_req{

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = (msg == null? null : msg.trim());
	}

}