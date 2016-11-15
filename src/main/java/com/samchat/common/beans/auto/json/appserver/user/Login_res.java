package com.samchat.common.beans.auto.json.appserver.user;

import java.util.ArrayList;
public class Login_res{

	private Long ret;
	private String token = "";
	private User user;
	private ArrayList<Sys_params> sys_params;

	public static class User {
		private Long id;
		private String username = "";
		private String countrycode = "";
		private String cellphone = "";
		private String email = "";
		private String address = "";
		private Long type;
		private My_settings my_settings;
		private Avatar avatar;
		private Long lastupdate;
		private Sam_pros_info sam_pros_info;

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

		public My_settings getMy_settings() {
			return my_settings;
		}

		public void setMy_settings(My_settings my_settings) {
			this.my_settings = my_settings;
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

		public Sam_pros_info getSam_pros_info() {
			return sam_pros_info;
		}

		public void setSam_pros_info(Sam_pros_info sam_pros_info) {
			this.sam_pros_info = sam_pros_info;
		}
	}

	public static class My_settings {
		private Long question_notify;

		public Long getQuestion_notify() {
			return question_notify;
		}

		public void setQuestion_notify(Long question_notify) {
			this.question_notify = question_notify;
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

	public static class Sam_pros_info {
		private String company_name = "";
		private String service_category = "";
		private String service_description = "";
		private String countrycode = "";
		private String phone = "";
		private String email = "";
		private String address = "";

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

	public static class Sys_params {
		private String param_code = "";
		private String param_value = "";

		public String getParam_code() {
			return param_code;
		}

		public void setParam_code(String param_code) {
			this.param_code = (param_code == null? "" : param_code.trim());
		}

		public String getParam_value() {
			return param_value;
		}

		public void setParam_value(String param_value) {
			this.param_value = (param_value == null? "" : param_value.trim());
		}
	}

	public Long getRet() {
		return ret;
	}

	public void setRet(Long ret) {
		this.ret = ret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = (token == null? "" : token.trim());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Sys_params> getSys_params() {
		return sys_params;
	}

	public void setSys_params(ArrayList<Sys_params> sys_params) {
		this.sys_params = sys_params;
	}

}