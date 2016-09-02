package com.samchat.common.beans.auto.json.appserver.profile;

public class AvatarUpdate_req{

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
		private Avatar avatar;

		public Avatar getAvatar() {
			return avatar;
		}

		public void setAvatar(Avatar avatar) {
			this.avatar = avatar;
		}
	}

	public static class Avatar {
		private String origin;
		private String thumb;

		public String getOrigin() {
			return origin;
		}

		public void setOrigin(String origin) {
			this.origin = (origin == null? null : origin.trim());
		}

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = (thumb == null? null : thumb.trim());
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