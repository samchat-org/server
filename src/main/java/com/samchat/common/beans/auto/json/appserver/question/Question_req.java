package com.samchat.common.beans.auto.json.appserver.question;

public class Question_req{

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
		private String question = "";
		private Location location;

		public long getOpt() {
			return opt;
		}

		public void setOpt(long opt) {
			this.opt = opt;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = (question == null? "" : question.trim());
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}
	}

	public static class Location {
		private Location_info location_info;
		private String place_id = "";
		private String address = "";

		public Location_info getLocation_info() {
			return location_info;
		}

		public void setLocation_info(Location_info location_info) {
			this.location_info = location_info;
		}

		public String getPlace_id() {
			return place_id;
		}

		public void setPlace_id(String place_id) {
			this.place_id = (place_id == null? "" : place_id.trim());
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = (address == null? "" : address.trim());
		}
	}

	public static class Location_info {
		private String longitude = "";
		private String latitude = "";

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = (longitude == null? "" : longitude.trim());
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = (latitude == null? "" : latitude.trim());
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