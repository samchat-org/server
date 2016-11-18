package com.samchat.common.beans.auto.json.appserver.profile;

public class CreateSamchatId_req{

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
		private String samchat_id;

		public String getSamchat_id() {
			return samchat_id;
		}

		public void setSamchat_id(String samchat_id) {
			this.samchat_id = (samchat_id == null? null : samchat_id.trim());
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