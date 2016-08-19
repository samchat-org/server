package com.samchat;

import com.samchat.common.utils.StrUtils;

import redis.clients.jedis.Jedis;

public class Jtest {

	 

    // redis服务器主机

    static String host = "samchat.unebf5.0001.cnn1.cache.amazonaws.com.cn";

    // 端口号

    static int port = 6379;

    public static void main(String[] args) {
    	System.out.print(Jtest.class.getClassLoader().getResource("config/spring").getPath());
    }

}
