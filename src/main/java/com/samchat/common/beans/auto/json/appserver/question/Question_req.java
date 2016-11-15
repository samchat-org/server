package com.samchat.common.beans.auto.json.appserver.question;

public class Question_req{

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
		private Long opt;
		private String question;
		private Location location;

		public Long getOpt() {
			return opt;
		}

		public void setOpt(Long opt) {
			this.opt = opt;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = (question == null? null : question.trim());
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
		private Scell scell;
		private String place_id;
		private String address;

		public Location_info getLocation_info() {
			return location_info;
		}

		public void setLocation_info(Location_info location_info) {
			this.location_info = location_info;
		}

		public Scell getScell() {
			return scell;
		}

		public void setScell(Scell scell) {
			this.scell = scell;
		}

		public String getPlace_id() {
			return place_id;
		}

		public void setPlace_id(String place_id) {
			this.place_id = (place_id == null? null : place_id.trim());
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = (address == null? null : address.trim());
		}
	}

	public static class Location_info {
		private String longitude;
		private String latitude;

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = (longitude == null? null : longitude.trim());
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = (latitude == null? null : latitude.trim());
		}
	}

	public static class Scell {
		private String mcc;
		private String mnc;
		private String lac;
		private String cellid;

		public String getMcc() {
			return mcc;
		}

		public void setMcc(String mcc) {
			this.mcc = (mcc == null? null : mcc.trim());
		}

		public String getMnc() {
			return mnc;
		}

		public void setMnc(String mnc) {
			this.mnc = (mnc == null? null : mnc.trim());
		}

		public String getLac() {
			return lac;
		}

		public void setLac(String lac) {
			this.lac = (lac == null? null : lac.trim());
		}

		public String getCellid() {
			return cellid;
		}

		public void setCellid(String cellid) {
			this.cellid = (cellid == null? null : cellid.trim());
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