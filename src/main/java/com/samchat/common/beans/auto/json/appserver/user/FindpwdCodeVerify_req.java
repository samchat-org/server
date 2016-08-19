package com.samchat.common.beans.auto.json.appserver.user;

public class FindpwdCodeVerify_req{

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
		private String cellphone = "";
		private String verifycode = "";
		private String deviceid = "";

		public String getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(String countrycode) {
			this.countrycode = (countrycode == null? "" : countrycode.trim());
		}

		public String getCellphone() {
			return cellphone;
		}

		public void setCellphone(String cellphone) {
			this.cellphone = (cellphone == null? "" : cellphone.trim());
		}

		public String getVerifycode() {
			return verifycode;
		}

		public void setVerifycode(String verifycode) {
			this.verifycode = (verifycode == null? "" : verifycode.trim());
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