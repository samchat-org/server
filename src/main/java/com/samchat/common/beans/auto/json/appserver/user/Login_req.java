package com.samchat.common.beans.auto.json.appserver.user;

public class Login_req{

	private Header header;
	private Body body;

	public static class Header {
		private String action = "";

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = (action == null? "" : action.trim());
		}
	}

	public static class Body {
		private String countrycode = "";
		private String account = "";
		private String pwd = "";
		private String deviceid = "";

		public String getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(String countrycode) {
			this.countrycode = (countrycode == null? "" : countrycode.trim());
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = (account == null? "" : account.trim());
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = (pwd == null? "" : pwd.trim());
		}

		public String getDeviceid() {
			return deviceid;
		}

		public void setDeviceid(String deviceid) {
			this.deviceid = (deviceid == null? "" : deviceid.trim());
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}