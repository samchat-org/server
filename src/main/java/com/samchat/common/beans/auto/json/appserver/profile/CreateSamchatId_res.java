package com.samchat.common.beans.auto.json.appserver.profile;

public class CreateSamchatId_res{

	private Long ret;
	private User user;

	public static class User {
		private Long lastupdate;

		public Long getLastupdate() {
			return lastupdate;
		}

		public void setLastupdate(Long lastupdate) {
			this.lastupdate = lastupdate;
		}
	}

	public Long getRet() {
		return ret;
	}

	public void setRet(Long ret) {
		this.ret = ret;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}