package com.samchat.common.beans.auto.json.appserver.user;

public class AppkeyGet_req{

	private Header header;
	private Body body;

	public static class Header {
		private String action;
		private String token;

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = (action == null?action : action.trim());
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = (token == null?token : token.trim());
		}
	}

	public static class Body {
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