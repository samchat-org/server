package com.samchat.common.beans.auto.json.appserver.user;

public class QueryAccurate_req{

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
		private long type;
		private String cellphone = "";
		private String unique_id  = "";
		private String username = "";

		public long getType() {
			return type;
		}

		public void setType(long type) {
			this.type = type;
		}

		public String getCellphone() {
			return cellphone;
		}

		public void setCellphone(String cellphone) {
			this.cellphone = (cellphone == null? "" : cellphone.trim());
		}

		public String getUnique_id () {
			return unique_id ;
		}

		public void setUnique_id (String unique_id ) {
			this.unique_id  = (unique_id  == null? "" : unique_id .trim());
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = (username == null? "" : username.trim());
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