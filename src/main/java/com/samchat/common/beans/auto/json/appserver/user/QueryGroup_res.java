package com.samchat.common.beans.auto.json.appserver.user;

import java.util.ArrayList;
public class QueryGroup_res{

	private long ret;
	private long count;
	private ArrayList<Users> users;

	public static class Avatar {
		private String origin;
		private String thumb;

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

	public static class Sam_pros_info {
		private String company_name;
		private String service_category;
		private String service_description;
		private String countrycode;
		private String phone;
		private String email;
		private String address;

		public String getCompany_name() {
			return company_name;
		}

		public void setCompany_name(String company_name) {
			this.company_name = (company_name == null? "" : company_name.trim());
		}

		public String getService_category() {
			return service_category;
		}

		public void setService_category(String service_category) {
			this.service_category = (service_category == null? "" : service_category.trim());
		}

		public String getService_description() {
			return service_description;
		}

		public void setService_description(String service_description) {
			this.service_description = (service_description == null? "" : service_description.trim());
		}

		public String getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(String countrycode) {
			this.countrycode = (countrycode == null? "" : countrycode.trim());
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = (phone == null? "" : phone.trim());
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
	}

	public static class Users {
		private long id;
		private String username;
		private String countrycode;
		private String cellphone;
		private String email;
		private String address;
		private long type;
		private Avatar avatar;
		private long lastupdate;
		private Sam_pros_info sam_pros_info;

		public long getId() {
			return id;
		}

		public void setId(long id) {
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

		public long getType() {
			return type;
		}

		public void setType(long type) {
			this.type = type;
		}

		public Avatar getAvatar() {
			return avatar;
		}

		public void setAvatar(Avatar avatar) {
			this.avatar = avatar;
		}

		public long getLastupdate() {
			return lastupdate;
		}

		public void setLastupdate(long lastupdate) {
			this.lastupdate = lastupdate;
		}

		public Sam_pros_info getSam_pros_info() {
			return sam_pros_info;
		}

		public void setSam_pros_info(Sam_pros_info sam_pros_info) {
			this.sam_pros_info = sam_pros_info;
		}
	}

	public long getRet() {
		return ret;
	}

	public void setRet(long ret) {
		this.ret = ret;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public ArrayList<Users> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Users> users) {
		this.users = users;
	}

}