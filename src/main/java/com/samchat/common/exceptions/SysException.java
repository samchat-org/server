package com.samchat.common.exceptions;

public class SysException extends RuntimeException {
	
	public SysException(String message, Throwable tw) {
		super(message, tw);
	}

	public SysException(String message) {
		super(message);
	}
}
