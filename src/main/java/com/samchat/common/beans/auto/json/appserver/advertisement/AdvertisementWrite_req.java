package com.samchat.common.beans.auto.json.appserver.advertisement;

import com.samchat.common.exceptions.AppException;
import com.samchat.common.enums.app.ResCodeAppEnum;
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
		private Long type;
		private String content;
		private String content_thumb;
		private String message_id;

		public Long getType() {
			return type;
		}

		public void setType(Long type) {
			if (type != null&&type != 0 &&type != 1 &&type != 2 ){
				 throw new AppException(ResCodeAppEnum.PARAM_NONSUPPORT.getCode(), "value:" + type);
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

		public String getMessage_id() {
			return message_id;
		}

		public void setMessage_id(String message_id) {
			this.message_id = (message_id == null? null : message_id.trim());
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