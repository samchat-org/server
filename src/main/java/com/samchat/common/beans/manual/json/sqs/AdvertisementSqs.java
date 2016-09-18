package com.samchat.common.beans.manual.json.sqs;

import org.apache.commons.lang.StringUtils;

public class AdvertisementSqs {
	
	private long ads_id;
	private long user_id;
	private long type;
	private String content;
	private String content_thumb;
	private long time;
	private byte sendType;
	
	public byte getSendType() {
		return sendType;
	}
	public void setSendType(byte sendType) {
		this.sendType = sendType;
	}
	public String getContent_thumb() {
		return content_thumb;
	}
	public void setContent_thumb(String content_thumb) {
		this.content_thumb = StringUtils.trimToEmpty(content_thumb);
	}
	public long getAds_id() {
		return ads_id;
	}
	public void setAds_id(long ads_id) {
		this.ads_id = ads_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
