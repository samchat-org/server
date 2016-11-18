package com.samchat.common.beans.manual.db;

import java.sql.Timestamp;

public class QryUserInfoVO {

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public Timestamp getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getService_category() {
		return service_category;
	}

	public void setService_category(String service_category) {
		this.service_category = service_category;
	}

	public String getService_description() {
		return service_description;
	}

	public void setService_description(String service_description) {
		this.service_description = service_description;
	}

	public String getCountrycode_pro() {
		return countrycode_pro;
	}

	public void setCountrycode_pro(String countrycode_pro) {
		this.countrycode_pro = countrycode_pro;
	}

	public String getPhone_pro() {
		return phone_pro;
	}

	public void setPhone_pro(String phone_pro) {
		this.phone_pro = phone_pro;
	}

	public String getEmail_pro() {
		return email_pro;
	}

	public void setEmail_pro(String email_pro) {
		this.email_pro = email_pro;
	}

	public String getAddress_pro() {
		return address_pro;
	}

	public void setAddress_pro(String address_pro) {
		this.address_pro = address_pro;
	}

	
	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}


	private long user_id;
	private String user_code;
	private String user_name;
	private String countrycode;
	private String cellphone;
	private String email;
	private String address;
	private long type;
	private String origin;
	private String thumb;
	private Timestamp lastupdate;

	private String company_name;
	private String service_category;
	private String service_description;
	private String countrycode_pro;
	private String phone_pro;
	private String email_pro;
	private String address_pro;
}
