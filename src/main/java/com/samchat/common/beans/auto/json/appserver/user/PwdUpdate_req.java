package com.samchat.common.beans.auto.json.appserver.user;

public class PwdUpdate_req{

	private Header header;
	private Body body;

	public static class Header {
		private String action = "";
		private String token = "";

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = (action == null? "" : action.trim());
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = (token == null? "" : token.trim());
		}
	}

	public static class Body {
		private String old_pwd = "";
		private String new_pwd = "";

		public String getOld_pwd() {
			return old_pwd;
		}

		public void setOld_pwd(String old_pwd) {
			this.old_pwd = (old_pwd == null? "" : old_pwd.trim());
		}

		public String getNew_pwd() {
			return new_pwd;
		}

		public void setNew_pwd(String new_pwd) {
			this.new_pwd = (new_pwd == null? "" : new_pwd.trim());
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