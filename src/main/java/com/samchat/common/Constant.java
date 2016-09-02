package com.samchat.common;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public interface Constant {

	SimpleDateFormat SDF_YYYYMM = new SimpleDateFormat("yyyyMM");

	int DEV_MODE = 1;

	String TWILLO_VERIFICATION_CODE = "\\{verification_code\\}";

	String TWILLO_INVITE_USER = "\\{invite_user\\}";

	String CHARSET = "utf-8";

	String RES_FMT = "application/json";

	String APP_ERR_PREFIX = "app_err";

	String REQ_FIELD_TOKEN = "token";

	byte QST_OPT_SEND = 0;

	static byte QUESTION_SEND_BLOCK = 1;
	static byte QUESTION_SEND_UNBLOCK = 0;

	byte OA_FAVOURITE = 1;
	byte OA_UNFAVOURITE = 0;

	byte OA_BLOCK = 1;
	byte OA_UNBLOCK = 0;

	byte OA_FOLLOW = 1;
	byte OA_UNFOLLOW = 0;

	byte ADS_UNBLOCK = 0;
	byte ADS_BLOCK = 1;

	byte SYS_LOCK = 2;
	byte SYS_UNLOCK = 1;

	byte USER_TYPE_CUSTOMER = 0;
	byte USER_TYPE_SERVICES = 1;

	Pattern EMAIL_PATTERN = Pattern.compile("^([\\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
	Pattern PHONE_PATTERN = Pattern.compile("^\\d+$");

	byte STATE_IN_USE = 1;
	byte STATE_NOT_IN_USE = 0;

	int REGISTER_TYPE_PHONE = 0;
	int REGISTER_TYPE_EMAIL = 1;

	interface SEQUENCE {
		String S_QUESTION = "s_question";
		String S_ADVERTISEMENT = "s_advertisement";
	}

	interface AWS_REGION {
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
		String REGISTER_CODE_TIME_TO_IDLE = "register_code_time_to_idle";
		String FIND_PASSWORD_CODE_TIME_TO_IDLE = "find_password_code_time_to_idle";
		String TWILIO_VERIFICATION_REGISTER_CODE_SMS_TEMPLETE = "twillo_verification_register_code_sms_templete";
		String TWILIO_VERIFICATION_FINDPWD_CODE_SMS_TEMPLETE = "twillo_verification_findpwd_code_sms_templete";
		String TWILIO_SEND_INVITE_SMS_TEMPLETE = "twillo_send_invite_sms_templete";
		String TWILIO_PHONE_NO = "twillo_phone_no";

		String SQS_ADVERTISEMENT = "aws_sqs_advertisement_url";
		String SQS_QUESTION = "aws_sqs_question_url";
	}

	int SUCCESS = 0;

	interface ERROR {
		int DATA_PARSE = -1;
		int ACTION_NONSUPPORT = -2;
		int PARAM_NONSUPPORT = -3;
		int TOKEN_FORMAT = -4;
		int INNER = -103;
		int PHONEorUSERNAME_EXIST = -201;
		int PHONE_FORMAT_ILLEGAL = -202;
		int USER_PWD = -203;
		int PHONE_NOT_EXIST = -204;
		int VERIFICATION_CODE = -205;
		int VERIFICATION_CODE_FREQUENT = -206;
		int USER_NOT_EXIST = -207;
		int REGISTER_CODE_EXPIRED = -211;
		int TOKEN_ILLEGAL = -401;

		int USER_PROS_EXIST = -501;
		int USER_OLD_PWD = -502;
		int USER_PROS_NOT_EXIST = -503;
		int OFFICIAL_ACCOUNT_UNFOLLOWED = -507;
		int SEND_QUESTION_FREQUENT = -509;
		int CUSTORMER_ADD_CUSTORMER = -510;
		int CUSTORMER_ADD_SERVIER_CONTACT_LIST = -511;
		 
	}

}
