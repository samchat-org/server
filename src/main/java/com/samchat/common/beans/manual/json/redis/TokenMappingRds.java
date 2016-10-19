package com.samchat.common.beans.manual.json.redis;

public class TokenMappingRds {

	private long userId;
	
	private long nowVersion;

	public long getNowVersion() {
		return nowVersion;
	}

	public void setNowVersion(long nowVersion) {
		this.nowVersion = nowVersion;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
