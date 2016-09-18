package com.samchat.common.beans.auto.json.appserver.profile;

import com.samchat.common.exceptions.AppException;
import com.samchat.common.enums.Constant;
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
		private String countrycode;
		private String cellphone;
		private String email;
		private String address;
		private Sam_pros_info sam_pros_info;

		public String getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(String countrycode) {
			if ("".equals(countrycode) || countrycode== null){
				 throw new AppException(Constant.ERROR.PARAM_NONSUPPORT, "value:" + countrycode);
			}
			this.countrycode = (countrycode == null? null : countrycode.trim());
		}

		public String getCellphone() {
			return cellphone;
		}

		public void setCellphone(String cellphone) {
			if ("".equals(cellphone) || cellphone== null){
				 throw new AppException(Constant.ERROR.PARAM_NONSUPPORT, "value:" + cellphone);
			}
			this.cellphone = (cellphone == null? null : cellphone.trim());
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = (email == null? null : email.trim());
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = (address == null? null : address.trim());
		}

		public Sam_pros_info getSam_pros_info() {
			return sam_pros_info;
		}

		public void setSam_pros_info(Sam_pros_info sam_pros_info) {
			this.sam_pros_info = sam_pros_info;
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = (address == null? null : address.trim());
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