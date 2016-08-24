package com.samchat.common;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public interface Constant {

	public static SimpleDateFormat SDF_YYYYMM = new SimpleDateFormat("yyyyMM");

	public static int DEV_MODE = 1;

	public String TWILLO_VERIFICATION_CODE = "\\{verification_code\\}";
	
	public String CHARSET = "utf-8";
	
	public String RES_FMT = "application/json";

	public String APP_ERR_PREFIX = "app_err";

	public String REQ_FIELD_TOKEN = "token";

	public byte QST_OPT_SEND = 0;
	
	interface SEQUENCE {
		String S_QUESTION = "s_question";
	}
	
	interface AWS_REGION{
		String CN_NORTH_1 = "cn-north-1";
	}

	interface CACHE_NAME {
		String LOGIN_ERR = "LOGIN_ERR";
		String USER_INFO = "USER_INFO";
		String QUESTION_SEND_CONTROL = "QUESTION_SEND_CONTROL";
		String TOKEN = "TOKEN";
		String REGISTER_CODE = "REGISTER_CODE";
		String SYS_CONFIG = "SYS_CONFIG";
		String FIND_PASSWORD_CODE = "FIND_PASSWORD_CODE";
	}

	interface SYS_PARAM_KEY {
		public String REGISTER_CODE_TIME_TO_IDLE = "register_code_time_to_idle";
		public String FIND_PASSWORD_CODE_TIME_TO_IDLE = "find_password_code_time_to_idle";
		public String TWILIO_VERIFICATION_REGISTER_CODE_SMS_TEMPLETE = "twillo_verification_register_code_sms_templete";
		public String TWILIO_VERIFICATION_FINDPWD_CODE_SMS_TEMPLETE = "twillo_verification_findpwd_code_sms_templete";
		public String TWILIO_PHONE_NO = "twillo_phone_no";
	}
	
	public int SUCCESS = 0;
	interface ERROR {
		public int DATA_PARSE = -1;
		public int ACTION_NONSUPPORT = -2;
		public int PARAM_NONSUPPORT = -3;
		public int TOKEN_FORMAT = -4;
		public int INNER = -103;
		public int PHONEorUSERNAME_EXIST = -201;
		public int PHONE_FORMAT_ILLEGAL = -202;
		public int USER_PWD = -203;
		public int PHONE_NOT_EXIST = -204;
		public int VERIFICATION_CODE = -205;
		public int VERIFICATION_CODE_FREQUENT = -206;
		public int USER_NOT_EXIST = -207;
		public int REGISTER_CODE_EXPIRED = -211;
		public int TOKEN_ILLEGAL = -401;

		public int USER_PROS_EXIST = -501;
		public int USER_OLD_PWD = -502;
		public int SEND_QUESTION_FREQUENT = -509;
	}

	
	public static byte QUESTION_SEND_BLOCK = 1;
	public static byte QUESTION_SEND_UNBLOCK = 0;

	public byte SYS_LOCK = 2;
	public byte SYS_UNLOCK = 1;

	public byte USER_TYPE_CUSTOMER = 0;
	public byte USER_TYPE_SERVICES = 1;

	public Pattern EMAIL_PATTERN = Pattern.compile("^([\\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
	public Pattern PHONE_PATTERN = Pattern.compile("^\\d+$");

	public byte STATE_IN_USE = 1;
	public byte STATE_NOT_IN_USE = 0;

	public int REGISTER_TYPE_PHONE = 0;
	public int REGISTER_TYPE_EMAIL = 1;

}
