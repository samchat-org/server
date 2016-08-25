package com.samchat.common.beans.auto.json.appserver.officialAccount;

public class Block_req{

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
		private long opt;
		private long id;

		public long getOpt() {
			return opt;
		}

		public void setOpt(long opt) {
			this.opt = opt;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
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