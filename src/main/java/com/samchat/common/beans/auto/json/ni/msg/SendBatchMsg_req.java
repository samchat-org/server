package com.samchat.common.beans.auto.json.ni.msg;

public class SendBatchMsg_req{

	private String fromAccid;
	private String toAccids;
	private String type;
	private String body;
	private String option;
	private String pushcontent;
	private String payload;
	private String ext;

	public String getFromAccid() {
		return fromAccid;
	}

	public void setFromAccid(String fromAccid) {
		this.fromAccid = (fromAccid == null? null : fromAccid.trim());
	}

	public String getToAccids() {
		return toAccids;
	}

	public void setToAccids(String toAccids) {
		this.toAccids = (toAccids == null? null : toAccids.trim());
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

}