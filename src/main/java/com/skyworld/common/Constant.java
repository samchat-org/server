package com.skyworld.common;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public interface Constant {

	interface CACHE_NAME {
		String TOKEN_CACHE = "TOKEN_CACHE";
		String REGISTER_CODE_CACHE = "REGISTER_CODE_CACHE";
		String SYS_CONFIG_CACHE = "SYS_CONFIG_CACHE";
	}

	interface NI_ACTION {
		String CREATE = "https://api.netease.im/nimserver/user/create.action";
	}

	public byte USER_TYPE_CUSTOMER = 0;
	public byte USER_TYPE_SERVICES = 1;

	public Pattern EMAIL_PATTERN = Pattern
			.compile("^([\\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
	public Pattern PHONE_PATTERN = Pattern.compile("^\\d+$");

	public byte STATE_IN_USE = 1;
	public byte STATE_NOT_IN_USE = 0;

	public int REGISTER_TYPE_PHONE = 0;
	public int REGISTER_TYPE_EMAIL = 1;

	public String CHARSET = "utf-8";
	public String RES_FMT = "application/json";

	public int SUCCESS = 0;
	public int ERROR_DATA_PARSE = -1;
	public int ERROR_ACTION_NONSUPPORT = -2;
	public int ERROR_PARAM_NONSUPPORT = -3;
	public int ERROR_TOKEN_FORMAT = -4;
	public int ERROR_INNER = -103;
	public int ERROR_PHONEorUSERNAME_EXIST = -201;
	public int ERROR_PHONE_FORMAT_ILLEGAL = -202;
	 
	public int ERROR_REGISTER_CODE_FREQUENT = -206;

	public int ERROR_TOKEN_ILLEGAL = -401;

}
