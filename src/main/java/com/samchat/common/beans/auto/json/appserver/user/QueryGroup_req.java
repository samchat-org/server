package com.samchat.common.beans.auto.json.appserver.user;

import java.util.ArrayList;
public class QueryGroup_req{

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
		private Param param;

		public long getOpt() {
			return opt;
		}

		public void setOpt(long opt) {
			this.opt = opt;
		}

		public Param getParam() {
			return param;
		}

		public void setParam(Param param) {
			this.param = param;
		}
	}

	public static class Param {
		private ArrayList<Long> unique_id;

		public ArrayList<Long> getUnique_id() {
			return unique_id;
		}

		public void setUnique_id(ArrayList<Long> unique_id) {
			this.unique_id = unique_id;
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