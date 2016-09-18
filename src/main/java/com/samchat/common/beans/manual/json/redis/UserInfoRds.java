package com.samchat.common.beans.manual.json.redis;

import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;

public class UserInfoRds extends TUserUsers {

	private UserInfoProRds userInfoProRds;

	public UserInfoProRds getUserInfoProRds() {
		return userInfoProRds;
	}

	public void setUserInfoProRds(UserInfoProRds userInfoProRds) {
		this.userInfoProRds = userInfoProRds;
	}

}
