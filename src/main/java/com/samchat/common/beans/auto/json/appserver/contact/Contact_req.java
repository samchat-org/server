package com.samchat.common.beans.auto.json.appserver.contact;

import com.samchat.common.exceptions.AppException;
import com.samchat.common.enums.app.ResCodeAppEnum;
public class Contact_req{

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
		private long opt;
		private long type;
		private long id;

		public long getOpt() {
			return opt;
		}

		public void setOpt(long opt) {
			if (opt != 0 &&opt != 1 ){
				 throw new AppException(ResCodeAppEnum.PARAM_NONSUPPORT.getCode(), "value:" + opt);
			}
			this.opt = opt;
		}

		public long getType() {
			return type;
		}

		public void setType(long type) {
			if (type != 0 &&type != 1 ){
				 throw new AppException(ResCodeAppEnum.PARAM_NONSUPPORT.getCode(), "value:" + type);
			}
			this.type = type;
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