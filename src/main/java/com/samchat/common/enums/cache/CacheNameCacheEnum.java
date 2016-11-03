package com.samchat.common.enums.cache;

/**
 * @author cl
 *
 */
public enum CacheNameCacheEnum {
	
	RDS_LOGIN_ERR("LOGIN_ERR"), 
	RDS_USER_INFO("USER_INFO"), 
	RDS_USER_INFO_ID("USER_INFO_ID"), 
	RDS_QUESTION_SEND_CONTROL("QUESTION_SEND_CONTROL"), 
	RDS_TOKEN("TOKEN"), 
	RDS_REGISTER_CODE("REGISTER_CODE"), 
	ECH_SYS_CONFIG("SYS_CONFIG"), 
	RDS_FIND_PASSWORD_CODE("FIND_PASSWORD_CODE"),
	EDIT_CELL_PHONE("EDIT_CELL_PHONE");
	
	private String value;
	
	private CacheNameCacheEnum(String value){
		this.value = value;
	}
	
	public String val(){
		return value;
	}

}
