package com.samchat.common.enums;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public interface Constant {

	SimpleDateFormat SDF_YYYYMM = new SimpleDateFormat("yyyyMM");
	
	SimpleDateFormat SDF_YYYYMMDDHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	String TWILLO_VERIFICATION_CODE = "\\{verification_code\\}";

	String TWILLO_INVITE_USER = "\\{invite_user\\}";

	String CHARSET = "utf-8";

	String RES_FMT = "application/json";

	String APP_ERR_PREFIX = "app_err";

	String REQ_FIELD_TOKEN = "token";

	byte QST_OPT_SEND = 0;

	byte QUESTION_SEND_BLOCK = 1;
	byte QUESTION_SEND_UNBLOCK = 0;

	byte OA_FAVOURITE = 1;
	byte OA_UNFAVOURITE = 0;

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

	interface SYS_PARAM_STATE {
		byte STATE_VALID_IN = 1;
		byte STATE_VALID_OUT = 2;
		byte STATE_INVALID = 0;
	}

	interface DATA_SOURCE {
		String DS_MASTER_0 = "ds_master_0";
		String DS_SHARDING = "ds_sharding";
	}
	
	interface SEQUENCE {
		String S_QUESTION = "s_question";
		String S_ADVERTISEMENT = "s_advertisement";
	}

	interface ADS_TYPE {
		long TXT = 0;
		long PIC = 1;
		long VID = 2;
	}

	int SUCCESS = 0;
	
	String NI_USER_PUBLIC_PREFIX = "public_";
	
	String SUPPORT_VERSION_PREFIX = "support_version_";

}
