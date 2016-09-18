package com.samchat.common.datas;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.samchat.common.enums.Constant;

@Component
@Aspect
public class DataSourceAspectAdvice implements Ordered {

	private static Logger log = Logger.getLogger(DataSourceAspectAdvice.class);

	@Before("execution(* com.samchat.service.*Srvs.*(..))")
	public void switchShardingSource() throws Throwable {
		DbContextHolder.setDbType(Constant.DATA_SOURCE.S_SHARDING);
	}

	@Before("execution(* com.samchat.service.*Srvm.*(..))")
	public void switchMainSource() throws Throwable {
		DbContextHolder.setDbType(Constant.DATA_SOURCE.S_MAIN);
	}

	@Override
	public int getOrder() {
 		return 1;
	}
}
