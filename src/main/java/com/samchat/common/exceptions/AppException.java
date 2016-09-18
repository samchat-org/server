package com.samchat.common.exceptions;

import com.samchat.common.enums.Constant;
import com.samchat.common.utils.CommonUtil;

public class AppException extends RuntimeException {

	private int errorCode;
 
	public AppException(int errorCode) {
		super(CommonUtil.getSysConfigStr(Constant.APP_ERR_PREFIX + errorCode));
		this.errorCode = errorCode;
	}

	public AppException(int errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}
	
	public AppException(int errorCode, Exception exception) {
		super(exception);
		this.errorCode = errorCode;
 	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
