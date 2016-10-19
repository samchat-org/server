package com.samchat.common.beans.manual.common;

import java.sql.Timestamp;

public class SysdateObjBean {
	
	private Timestamp now;
	
	private long nowVersion;

	public Timestamp getNow() {
		return now;
	}

	public void setNow(Timestamp now) {
		this.now = now;
	}

	public long getNowVersion() {
		return nowVersion;
	}

	public void setNowVersion(long nowVersion) {
		this.nowVersion = nowVersion;
	}
	
}
