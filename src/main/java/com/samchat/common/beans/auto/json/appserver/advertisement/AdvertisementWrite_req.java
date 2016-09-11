package com.samchat.common.beans.auto.json.appserver.advertisement;

import com.samchat.common.exceptions.AppException;
import com.samchat.common.Constant;
public class AdvertisementWrite_req{

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
		private String content;
		private String content_thumb;

		public long getType() {
			return type;
		}

		public void setType(long type) {
			if (type != 0 &&type != 1 ){
				 throw new AppException(Constant.ERROR.PARAM_NONSUPPORT, "value:" + type);
			}
			this.type = type;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = (content == null? null : content.trim());
		}

		public String getContent_thumb() {
			return content_thumb;
		}

		public void setContent_thumb(String content_thumb) {
			this.content_thumb = (content_thumb == null? null : content_thumb.trim());
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