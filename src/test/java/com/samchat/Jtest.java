package com.samchat;

import org.codehaus.jackson.map.ObjectMapper;

import com.samchat.common.beans.auto.json.appserver.user.Login_req;


public class Jtest {

	 

    // redis服务器主机

    static String host = "samchat.unebf5.0001.cnn1.cache.amazonaws.com.cn";

    // 端口号

    static int port = 6379;

    public static void main(String[] args) throws Exception{
    	ObjectMapper  om = new ObjectMapper();
    	Login_req req = om.readValue("{\"header\":{\"action\":\"\"},\"body\":{\"countrycode\":\"86\",\"account\":\"1381196123\",\"pwd\":\"123456\",\"deviceid\":\"14EF65\"}}", Login_req.class);
    	System.out.print(req.getClass());
    }

}
