package com.samchat.common.beans.auto.json.ni.msg;

public class SendAttachMsg_req{

	private String from;
	private String msgtype;
	private String to;
	private String attach;
	private String pushcontent;
	private String payload;
	private String sound;
	private String save;
	private String option;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = (from == null? null : from.trim());
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = (msgtype == null? null : msgtype.trim());
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = (to == null? null : to.trim());
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = (attach == null? null : attach.trim());
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

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = (sound == null? null : sound.trim());
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = (save == null? null : save.trim());
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = (option == null? null : option.trim());
	}

}