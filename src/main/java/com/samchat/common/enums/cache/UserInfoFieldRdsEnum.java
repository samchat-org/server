package com.samchat.common.enums.cache;

public enum UserInfoFieldRdsEnum {
	PUBLIC_TOKEN("ptk"),
	TOKEN("tk"), 
	USER_INFO("ui"), 
	PROS_INFO("pi"),
	CUSTOMER_LIST_DATE("cld"), 
	SERVICER_LIST_DATE("sld"), 
	FOLLOW_LIST_DATE("fld"),
	CLIENT_ID("ci");

	private String code;

	private UserInfoFieldRdsEnum(String code) {
		this.code = code;
	}

	public String val() {
		return code;
	}
}
