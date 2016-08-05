package com.skyworld.beans.auto.json.userTpl;

public class Register_req{

	private Header header;
	private Body body;

	public static class Header {
		private String action;

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}
	}

	public static class Body {
		private int type;
		private String email;
		private int countrycode;
		private String cellphone;
		private int verifycode;
		private String username;
		private String pwd;
		private String deviceid;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(int countrycode) {
			this.countrycode = countrycode;
		}

		public String getCellphone() {
			return cellphone;
		}

		public void setCellphone(String cellphone) {
			this.cellphone = cellphone;
		}

		public int getVerifycode() {
			return verifycode;
		}

		public void setVerifycode(int verifycode) {
			this.verifycode = verifycode;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}

		public String getDeviceid() {
			return deviceid;
		}

		public void setDeviceid(String deviceid) {
			this.deviceid = deviceid;
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