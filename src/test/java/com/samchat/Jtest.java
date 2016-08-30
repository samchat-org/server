package com.samchat;

import java.util.HashMap;

public class Jtest {

	 

    // redis服务器主机

    static String host = "samchat.unebf5.0001.cnn1.cache.amazonaws.com.cn";

    // 端口号

    static int port = 6379;

    public static void main(String[] args) throws Exception{
    	HashMap<String, Object> str = new HashMap<String, Object>(); 
    	str.put("1", new Long(1));
    	long r = (Long)str.get("1");
    	System.out.print(r);
    }

}
