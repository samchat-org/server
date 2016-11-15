package com.samchat.common.beans.auto.json.appserver.user;

public class CreateSamPros_res{

	private Long ret;
	private User user;

	public static class User {
		private Long id;
		private String username = "";
		private String countrycode = "";
		private String cellphone = "";
		private String email = "";
		private String address = "";
		private Long type;
		private Avatar avatar;
		private Long lastupdate;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = (username == null? "" : username.trim());
		}

		public String getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(String countrycode) {
			this.countrycode = (countrycode == null? "" : countrycode.trim());
		}

		public String getCellphone() {
			return cellphone;
		}

		public void setCellphone(String cellphone) {
			this.cellphone = (cellphone == null? "" : cellphone.trim());
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = (email == null? "" : email.trim());
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = (address == null? "" : address.trim());
		}

		public Long getType() {
			return type;
		}

		public void setType(Long type) {
			this.type = type;
		}

		public Avatar getAvatar() {
			return avatar;
		}

		public void setAvatar(Avatar avatar) {
			this.avatar = avatar;
		}

		public Long getLastupdate() {
			return lastupdate;
		}

		public void setLastupdate(Long lastupdate) {
			this.lastupdate = lastupdate;
		}
	}

	public static class Avatar {
		private String origin = "";
		private String thumb = "";

		public String getOrigin() {
			return origin;
		}

		public void setOrigin(String origin) {
			this.origin = (origin == null? "" : origin.trim());
		}

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = (thumb == null? "" : thumb.trim());
		}
	}

	public Long getRet() {
		return ret;
	}

	public void setRet(Long ret) {
		this.ret = ret;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}