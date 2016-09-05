package com.samchat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dangdang.ddframe.rdb.sharding.api.HintManager;
import com.dangdang.ddframe.rdb.sharding.example.config.spring.masterslave.service.OrderService;


public class Jtest {

	 

    // redis服务器主机

    static String host = "samchat.unebf5.0001.cnn1.cache.amazonaws.com.cn";

    // 端口号

    static int port = 6379;

    public static void main(String[] args) throws Exception{
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/spring/applicationContextWithMasterSlave.xml");

        // CHECKSTYLE:ON
             OrderService orderService =  applicationContext.getBean(OrderService.class);
            orderService.insert();
            orderService.select();
            orderService.delete();
            orderService.select();
        
    }

}
