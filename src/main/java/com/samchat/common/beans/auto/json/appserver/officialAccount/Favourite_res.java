package com.samchat.common.beans.auto.json.appserver.officialAccount;

public class Favourite_res{

	private long ret;
	private User user;

	public static class User {
		private long lastupdate;

		public long getLastupdate() {
			return lastupdate;
		}

		public void setLastupdate(long lastupdate) {
			this.lastupdate = lastupdate;
		}
	}

	public long getRet() {
		return ret;
	}

	public void setRet(long ret) {
		this.ret = ret;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}