package com.samchat.common.beans.auto.json.appserver.advertisement;

import java.util.ArrayList;
public class AdvertisementDelete_req{

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
		private ArrayList<Advertisements> advertisements;

		public ArrayList<Advertisements> getAdvertisements() {
			return advertisements;
		}

		public void setAdvertisements(ArrayList<Advertisements> advertisements) {
			this.advertisements = advertisements;
		}
	}

	public static class Advertisements {
		private Long adv_id;
		private Long publish_timestamp;

		public Long getAdv_id() {
			return adv_id;
		}

		public void setAdv_id(Long adv_id) {
			this.adv_id = adv_id;
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