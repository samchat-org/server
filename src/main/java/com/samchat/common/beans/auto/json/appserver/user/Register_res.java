package com.samchat.common.beans.auto.json.appserver.user;

import java.util.ArrayList;
public class Register_res{

	private long ret;
	private String token = "";
	private User user;
	private ArrayList<Sys_params> sys_params;

	public static class User {
		private long id;
		private long lastupdate;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getLastupdate() {
			return lastupdate;
		}

		public void setLastupdate(long lastupdate) {
			this.lastupdate = lastupdate;
		}
	}

	public static class Sys_params {
		private String param_code = "";
		private String param_value = "";

		public String getParam_code() {
			return param_code;
		}

		public void setParam_code(String param_code) {
			this.param_code = (param_code == null? "" : param_code.trim());
		}

		public String getParam_value() {
			return param_value;
		}

		public void setParam_value(String param_value) {
			this.param_value = (param_value == null? "" : param_value.trim());
		}
	}

	public long getRet() {
		return ret;
	}

	public void setRet(long ret) {
		this.ret = ret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = (token == null? "" : token.trim());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Sys_params> getSys_params() {
		return sys_params;
	}

	public void setSys_params(ArrayList<Sys_params> sys_params) {
		this.sys_params = sys_params;
	}

}