package com.samchat.common.beans.auto.json.appserver.profile;

public class GetPlacesInfoRequest_req{

	private Header header;
	private Body body;

	public static class Header {
		private String action;
		private String token;

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = (action == null? null : action.trim());
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = (token == null? null : token.trim());
		}
	}

	public static class Body {
		private String key;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = (key == null? null : key.trim());
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