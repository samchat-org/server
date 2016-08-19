package com.samchat.common;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public interface Constant {
	
	public static int DEV_MODE = 1;

	interface CACHE_NAME {
		String TOKEN_CACHE = "TOKEN_CACHE";
		String REGISTER_CODE_CACHE = "REGISTER_CODE_CACHE";
		String SYS_CONFIG_CACHE = "SYS_CONFIG_CACHE";
		String FIND_PASSWORD_CODE_CACHE = "FIND_PASSWORD_CODE_CACHE";
	}
	
	
	interface SYS_PARAM_KEY{
		public String REGISTER_CODE_TIME_TO_IDLE = "register_code_time_to_idle";
		public String FIND_PASSWORD_CODE_TIME_TO_IDLE = "find_password_code_time_to_idle";
  		public String TWILIO_VERIFICATION_REGISTER_CODE_SMS_TEMPLETE = "twillo_verification_register_code_sms_templete";
  		public String TWILIO_VERIFICATION_FINDPWD_CODE_SMS_TEMPLETE = "twillo_verification_findpwd_code_sms_templete";
		public String TWILIO_PHONE_NO = "twillo_phone_no";
	}

	public String TWILLO_VERIFICATION_CODE = "\\{verification_code\\}";
	
	public byte SYS_LOCK = 2;
	public byte SYS_UNLOCK = 1;

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

	public String APP_ERR_PREFIX = "app_err";
	public int SUCCESS = 0;
	public int ERROR_DATA_PARSE = -1;
	public int ERROR_ACTION_NONSUPPORT = -2;
	public int ERROR_PARAM_NONSUPPORT = -3;
	public int ERROR_TOKEN_FORMAT = -4;
	public int ERROR_INNER = -103;
	public int ERROR_PHONEorUSERNAME_EXIST = -201;
	public int ERROR_PHONE_FORMAT_ILLEGAL = -202;
	public int ERROR_USER_PWD = -203;
	public int ERROR_PHONE_NOT_EXIST = -204;
	public int ERROR_VERIFICATION_CODE = -205;
	public int ERROR_VERIFICATION_CODE_FREQUENT = -206;
	public int ERROR_USER_NOT_EXIST = -207;
	public int ERROR_REGISTER_CODE_EXPIRED = -211;

	public int ERROR_TOKEN_ILLEGAL = -401;
	
	public int ERROR_USER_PROS_EXIST = -501;
	public int ERROR_USER_OLD_PWD = -502;

}
