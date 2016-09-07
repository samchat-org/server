package com.samchat.common.datas;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.samchat.common.Constant;

@Component
@Aspect
public class DataSourceAspectAdvice {
	
	private static Logger log = Logger.getLogger(DataSourceAspectAdvice.class);

    @Before("execution(* com.samchat.service.*Srvs.*(..))")
    public void switchShardingSource() throws Throwable {
    	DbContextHolder.setDbType(Constant.DATA_SOURCE.S_SHARDING);
    }
    
    @Before("execution(* com.samchat.service.*Srvm.*(..))")
    public void switchMainSource() throws Throwable {
    	DbContextHolder.setDbType(Constant.DATA_SOURCE.S_MAIN);
    }
}
