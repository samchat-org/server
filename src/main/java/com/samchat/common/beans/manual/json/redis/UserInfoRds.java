package com.samchat.common.beans.manual.json.redis;

import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;

public class UserInfoRds extends TUserUsers {

	private long nowVersion;

	public long getNowVersion() {
		return nowVersion;
	}

	public void setNowVersion(long nowVersion) {
		this.nowVersion = nowVersion;
	}

}
