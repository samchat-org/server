package com.samchat.common.beans.auto.json.appserver.user;

import com.samchat.common.exceptions.AppException;
import com.samchat.common.enums.app.ResCodeAppEnum;
public class Login_req{

	private Header header;
	private Body body;

	public static class Header {
		private String action;

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = (action == null? null : action.trim());
		}
	}

	public static class Body {
		private Long type;
		private String countrycode;
		private String account;
		private String pwd;
		private String verifycode;
		private String deviceid;
		private String device_type;
		private String app_version;

		public Long getType() {
			if (type == null|| (type != 0 &&type != 1 &&type != 2 )){
				 throw new AppException(ResCodeAppEnum.PARAM_NONSUPPORT.getCode(), "value:" + type);
			}
			return type;
		}

		public void setType(Long type) {
			if (type == null|| (type != 0 &&type != 1 &&type != 2 )){
				 throw new AppException(ResCodeAppEnum.PARAM_NONSUPPORT.getCode(), "value:" + type);
			}
			this.type = type;
		}

		public String getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(String countrycode) {
			this.countrycode = (countrycode == null? null : countrycode.trim());
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = (account == null? null : account.trim());
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = (pwd == null? null : pwd.trim());
		}

		public String getVerifycode() {
			return verifycode;
		}

		public void setVerifycode(String verifycode) {
			this.verifycode = (verifycode == null? null : verifycode.trim());
		}

		public String getDeviceid() {
			return deviceid;
		}

		public void setDeviceid(String deviceid) {
			this.deviceid = (deviceid == null? null : deviceid.trim());
		}

		public String getDevice_type() {
			return device_type;
		}

		public void setDevice_type(String device_type) {
			this.device_type = (device_type == null? null : device_type.trim());
		}

		public String getApp_version() {
			return app_version;
		}

		public void setApp_version(String app_version) {
			this.app_version = (app_version == null? null : app_version.trim());
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