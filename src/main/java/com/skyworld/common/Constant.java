package com.skyworld.common;

import org.apache.log4j.Logger;

public class Constant {
	private static  final Logger log = Logger.getLogger(Constant.class);
	public static final int ERROR_DATA_PARSE = -1;
	public static final int ERROR_ACTION_NONSUPPORT = -2;
	public static final int ERROR_PARAM_NONSUPPORT = -3;
	public static final int ERROR_TOKEN_FORMAT = -4;
	public static final int ERROR_TOKEN_ILLEGAL = -401;
	
	public static void main(String args[]){
		Exception e = new Exception(new ClassNotFoundException());
		log.error( e.getMessage(),e);
	 
	}

}
