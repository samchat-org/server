package com.samchat.common.beans.auto.json.appserver.user;

public class QueryFuzzy_req{

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
		private Long count;
		private Long opt;
		private Param param;

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}

		public Long getOpt() {
			return opt;
		}

		public void setOpt(Long opt) {
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
		private String search_key;
		private Long search_type;

		public String getSearch_key() {
			return search_key;
		}

		public void setSearch_key(String search_key) {
			this.search_key = (search_key == null? null : search_key.trim());
		}

		public Long getSearch_type() {
			return search_type;
		}

		public void setSearch_type(Long search_type) {
			this.search_type = search_type;
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