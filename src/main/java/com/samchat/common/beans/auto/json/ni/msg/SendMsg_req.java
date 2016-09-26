package com.samchat.common.beans.auto.json.ni.msg;

public class SendMsg_req{

	private String from;
	private String ope;
	private String to;
	private String type;
	private String body;
	private String option;
	private String pushcontent;
	private String payload;
	private String ext;
	private String forcepushlist;
	private String forcepushcontent;
	private String forcepushall;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = (from == null? null : from.trim());
	}

	public String getOpe() {
		return ope;
	}

	public void setOpe(String ope) {
		this.ope = (ope == null? null : ope.trim());
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = (to == null? null : to.trim());
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = (type == null? null : type.trim());
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = (body == null? null : body.trim());
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = (option == null? null : option.trim());
	}

	public String getPushcontent() {
		return pushcontent;
	}

	public void setPushcontent(String pushcontent) {
		this.pushcontent = (pushcontent == null? null : pushcontent.trim());
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = (payload == null? null : payload.trim());
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = (ext == null? null : ext.trim());
	}

	public String getForcepushlist() {
		return forcepushlist;
	}

	public void setForcepushlist(String forcepushlist) {
		this.forcepushlist = (forcepushlist == null? null : forcepushlist.trim());
	}

	public String getForcepushcontent() {
		return forcepushcontent;
	}

	public void setForcepushcontent(String forcepushcontent) {
		this.forcepushcontent = (forcepushcontent == null? null : forcepushcontent.trim());
	}

	public String getForcepushall() {
		return forcepushall;
	}

	public void setForcepushall(String forcepushall) {
		this.forcepushall = (forcepushall == null? null : forcepushall.trim());
	}

}