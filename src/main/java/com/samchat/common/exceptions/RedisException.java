package com.samchat.common.exceptions;

public class RedisException extends RuntimeException{
	public RedisException(String message, Throwable tw){
		super(message, tw);
	}
}
