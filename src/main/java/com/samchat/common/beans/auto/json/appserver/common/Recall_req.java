package com.samchat.common.beans.auto.json.appserver.common;

public class Recall_req{

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
		private Long type;
		private String business_id;
		private Long publish_timestamp;

		public Long getType() {
			return type;
		}

		public void setType(Long type) {
			this.type = type;
		}

		public String getBusiness_id() {
			return business_id;
		}

		public void setBusiness_id(String business_id) {
			this.business_id = (business_id == null? null : business_id.trim());
		}

		public Long getPublish_timestamp() {
			return publish_timestamp;
		}

		public void setPublish_timestamp(Long publish_timestamp) {
			this.publish_timestamp = publish_timestamp;
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