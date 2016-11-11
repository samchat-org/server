package com.samchat.common.beans.auto.json.appserver.contact;

import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.exceptions.AppException;
public class ContactListQuery_req{

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
		private long type;

		public long getType() {
			return type;
		}

		public void setType(long type) {
			if (type != 0 &&type != 1 ){
				 throw new AppException(ResCodeAppEnum.PARAM_NONSUPPORT.getCode(), "value:" + type);
			}
			this.type = type;
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