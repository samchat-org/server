package com.samchat.common.beans.manual.json.redis;

public class TokenValRds {
	
	private String token;
	
	private long nowVersion;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getNowVersion() {
		return nowVersion;
	}

	public void setNowVersion(long nowVersion) {
		this.nowVersion = nowVersion;
	}
}
