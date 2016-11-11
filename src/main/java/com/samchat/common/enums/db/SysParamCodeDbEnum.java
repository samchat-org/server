package com.samchat.common.enums.db;



public enum SysParamCodeDbEnum {
	
	//sys
	SYSTEM_ID("system_id"),
	INTERNET_PROXY("internet_proxy"),
	
	//key of twillo
	TWILIO_PHONE_NO("twillo_phone_no"),
	TWILIO_ACCOUNT("twilio_account"),
	TWILIO_AUTH_TOKEN("twilio_auth_token"),
	TWILIO_ENABLE("twilio_enable"),
	
	//key of getui  
	GETUI_APP_ID("getui_app_id"),
	GETUI_APP_KEY("getui_app_key"),
	GETUI_APP_MASTER_SECRET("getui_app_master_secret"),
	GETUI_OFFLINE_TIME("getui_offline_time"),
	
	//key of google cfg
	GOOGLE_PLACES_KEY("google_places_key"),
	
	// key of ni cfg
	NI_APP_KEY("ni_app_key"),
	NI_APP_SECRET("ni_app_secret"),
	
	// key of twillo sms templete 
//	TWILIO_VERIFICATION_REGISTER_CODE_SMS_TEMPLETE("twillo_verification_register_code_sms_templete"),
//	TWILIO_VERIFICATION_FINDPWD_CODE_SMS_TEMPLETE("twillo_verification_findpwd_code_sms_templete"),
//	TWILIO_VERIFICATION_EDIT_CELL_PHONE_CODE_SMS_TEMPLETE("twillo_verification_edit_cell_phone_code_sms_templete"),
//	TWILIO_SEND_INVITE_SMS_TEMPLETE("twillo_send_invite_sms_templete"),
	
	// key of sqs cfg
	SQS_RECEIVE_WAIT_TIME("aws_sqs_receive_wait_time"),
	SQS_RECEIVE_VISIBILITY_TIME("aws_sqs_receive_visibility_time"),
	SQS_ADVERTISEMENT_URL("aws_sqs_advertisement_url"),
	SQS_QUESTION_URL("aws_sqs_question_url"),
	
	// key of s3 cfg
	S3_ENDPOINT("aws_s3_endpoint"),
	S3_AVATAR_THUMB_WIDTH("aws_s3_avatar_thumb_width"),
	S3_AVATAR_THUMB_HEIGHT("aws_s3_avatar_thumb_height"),
	S3_AVATAR_THUMB_QUALITY("aws_s3_avatar_thumb_quality"),

	// key of dispatcher advertisement cfg
	DISPATCHER_ADVERTISEMENT_THREAD_COUNT("dispatcher_advertisement_thread_count"),
	DISPATCHER_ADVERTISEMENT_VALID_CYCLE("dispatcher_advertisement_valid_cycle"),
	
	// key of dispatcher question cfg
	DISPATCHER_QUESTION_THREAD_COUNT("dispatcher_question_thread_count"),
	
	USER_REGISTER_CODE_TIME_TO_IDLE("register_code_time_to_idle"),
	USER_REGISTER_CODE_CTRL_TIME_TO_IDLE("register_code_ctrl_time_to_idle"),
	
	USER_FIND_PASSWORD_CODE_TIME_TO_IDLE("find_password_code_time_to_idle"),
	USER_FIND_PASSWORD_CODE_CTRL_TIME_TO_IDLE("find_password_code_ctrl_time_to_idle"),
	
	EDIT_CELL_PHONE_CODE_TIME_TO_IDLE("edit_cell_phone_code_time_to_idle"),
	EDIT_CELL_PHONE_CODE_CTRL_TIME_TO_IDLE("edit_cell_phone_code_ctrl_time_to_idle"),
	
	PAGE_SIZE_QUERYPUBLICLIST("page_size_queryPublicList"),
	PAGE_SIZE_QUERYUSERSFUZZY("page_size_queryUsersFuzzy"),
	PAGE_SIZE_QUERYFOLLOWLISTFORADSDSP("page_size_queryFollowListForAdsDsp"),
	
	QUESTION_SEND_BLOCK_TIME("question_send_block_time"),
	QUESTION_SEND_LIMIT_TIME("question_send_limit_time"),
	QUESTION_SEND_LIMIT_COUNT("question_send_limit_count");
	
	private String paramCode;
	
	private SysParamCodeDbEnum(String paramCode){
		this.paramCode = paramCode;
	}
	public String getParamCode(){
		return this.paramCode;
	}
}
