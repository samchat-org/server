package com.samchat.common.beans.auto.json.appserver.advertisement;

public class AdvertisementWrite_res{

	private long ret;
	private long adv_id;
	private String content_thumb = "";
	private long publish_timestamp;

	public long getRet() {
		return ret;
	}

	public void setRet(long ret) {
		this.ret = ret;
	}

	public long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(long adv_id) {
		this.adv_id = adv_id;
	}

	public String getContent_thumb() {
		return content_thumb;
	}

	public void setContent_thumb(String content_thumb) {
		this.content_thumb = (content_thumb == null? "" : content_thumb.trim());
	}

	public long getPublish_timestamp() {
		return publish_timestamp;
	}

	public void setPublish_timestamp(long publish_timestamp) {
		this.publish_timestamp = publish_timestamp;
	}

}