package com.samchat.common.enums.app;

public enum ResCodeAppEnum {
	SUCCESS(0),
	DATA_PARSE(-1),
	ACTION_NONSUPPORT(-2),
	PARAM_NONSUPPORT(-3),
	TOKEN_FORMAT(-4),
	INNER(-103),
	PHONEorUSERNAME_EXIST(-201),
	PHONE_FORMAT_ILLEGAL(-202),
	USER_PWD(-203),
	PHONE_NOT_EXIST(-204),
	VERIFICATION_CODE(-205),
	VERIFICATION_CODE_FREQUENT(-206),
	USER_NOT_EXIST(-207),
	REGISTER_CODE_EXPIRED(-211),
	TOKEN_ILLEGAL(-401),

	USER_PROS_EXIST(-501),
	USER_OLD_PWD(-502),
	USER_PROS_NOT_EXIST(-503),
	OFFICIAL_ACCOUNT_UNFOLLOWED(-507),
	SEND_QUESTION_FREQUENT(-509),
	CUSTORMER_ADD_CUSTORMER(-510),
	CUSTORMER_ADD_SERVIER_CONTACT_LIST(-511);
	
	private int errorCode;
	
	private ResCodeAppEnum(int errorCode){
		this.errorCode = errorCode;
	}
	
	public int getCode(){
		return errorCode;
	}
}
