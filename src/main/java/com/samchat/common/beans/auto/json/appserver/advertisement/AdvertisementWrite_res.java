package com.samchat.common.beans.auto.json.appserver.advertisement;

public class AdvertisementWrite_res{

	private Long ret;
	private Long adv_id;
	private String content_thumb = "";
	private Long publish_timestamp;

	public Long getRet() {
		return ret;
	}

	public void setRet(Long ret) {
		this.ret = ret;
	}

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public String getContent_thumb() {
		return content_thumb;
	}

	public void setContent_thumb(String content_thumb) {
		this.content_thumb = (content_thumb == null? "" : content_thumb.trim());
	}

	public Long getPublish_timestamp() {
		return publish_timestamp;
	}

	public void setPublish_timestamp(Long publish_timestamp) {
		this.publish_timestamp = publish_timestamp;
	}

}