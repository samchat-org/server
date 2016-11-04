package com.samchat.common.enums.db;

public enum SysParamCodeDbEnum {
	
	TWILIO_VERIFICATION_REGISTER_CODE_SMS_TEMPLETE("twillo_verification_register_code_sms_templete"),
	TWILIO_VERIFICATION_FINDPWD_CODE_SMS_TEMPLETE("twillo_verification_findpwd_code_sms_templete"),
	TWILIO_VERIFICATION_EDIT_CELL_PHONE_CODE_SMS_TEMPLETE("twillo_verification_edit_cell_phone_code_sms_templete"),
	
	TWILIO_SEND_INVITE_SMS_TEMPLETE("twillo_send_invite_sms_templete"),
	TWILIO_PHONE_NO("twillo_phone_no"),
	
	SQS_ADVERTISEMENT("aws_sqs_advertisement_url"),
	SQS_QUESTION("aws_sqs_question_url"),
	
	USER_REGISTER_CODE_TIME_TO_IDLE("register_code_time_to_idle"),
	USER_REGISTER_CODE_CTRL_TIME_TO_IDLE("register_code_ctrl_time_to_idle"),
	
	USER_FIND_PASSWORD_CODE_TIME_TO_IDLE("find_password_code_time_to_idle"),
	USER_FIND_PASSWORD_CODE_CTRL_TIME_TO_IDLE("find_password_code_ctrl_time_to_idle"),
	
	EDIT_CELL_PHONE_CODE_TIME_TO_IDLE("edit_cell_phone_code_time_to_idle"),
	EDIT_CELL_PHONE_CODE_CTRL_TIME_TO_IDLE("edit_cell_phone_code_ctrl_time_to_idle");
	
	private String paramCode;
	
	private SysParamCodeDbEnum(String paramCode){
		this.paramCode = paramCode;
	}
	public String getParamCode(){
		return this.paramCode;
	}
}
