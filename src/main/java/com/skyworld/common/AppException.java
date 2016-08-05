package com.skyworld.common;

public class AppException extends RuntimeException {

	private int errorCode;
 
	public AppException(int errorCode) {
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
