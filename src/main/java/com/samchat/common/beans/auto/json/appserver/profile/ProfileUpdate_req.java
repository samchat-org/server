package com.samchat.common.beans.auto.json.appserver.profile;

public class ProfileUpdate_req{

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
		private User user;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	}

	public static class User {
		private String email;
		private Location location;
		private Sam_pros_info sam_pros_info;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = (email == null? null : email.trim());
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public Sam_pros_info getSam_pros_info() {
			return sam_pros_info;
		}

		public void setSam_pros_info(Sam_pros_info sam_pros_info) {
			this.sam_pros_info = sam_pros_info;
		}
	}

	public static class Location {
		private Location_info location_info;
		private String place_id;
		private String address;

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

	public static class Sam_pros_info {
		private String company_name;
		private String service_category;
		private String service_description;
		private String countrycode;
		private String phone;
		private String email;
		private Location location;

		public String getCompany_name() {
			return company_name;
		}

		public void setCompany_name(String company_name) {
			this.company_name = (company_name == null? null : company_name.trim());
		}

		public String getService_category() {
			return service_category;
		}

		public void setService_category(String service_category) {
			this.service_category = (service_category == null? null : service_category.trim());
		}

		public String getService_description() {
			return service_description;
		}

		public void setService_description(String service_description) {
			this.service_description = (service_description == null? null : service_description.trim());
		}

		public String getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(String countrycode) {
			this.countrycode = (countrycode == null? null : countrycode.trim());
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = (phone == null? null : phone.trim());
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = (email == null? null : email.trim());
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
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