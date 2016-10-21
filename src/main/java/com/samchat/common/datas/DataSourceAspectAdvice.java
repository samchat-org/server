package com.samchat.common.datas;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.rdb.sharding.api.HintManager;
import com.samchat.common.enums.Constant;

@Component
@Aspect
public class DataSourceAspectAdvice implements Ordered {

	private static Logger log = Logger.getLogger(DataSourceAspectAdvice.class);
	
	@Before("execution(* com.samchat.service.*Srvs.*(..))")
	public void switchShardingSource() throws Throwable {
		log.info("switchShardingSource");
		DbContextHolder.setDbType(Constant.DATA_SOURCE.DS_SHARDING);
	}

	@Before("execution(* com.samchat.service.*Srvs.*_master(..))")
	public void switchShardingSourceMaster() throws Throwable {
		log.info("switchShardingSourceMaster");
 		HintManager.getInstance().setMasterRouteOnly();
	}

	@Before("execution(* com.samchat.service.*Srvm.*(..))")
	public void switchMasterSource() throws Throwable {
		DbContextHolder.setDbType(Constant.DATA_SOURCE.DS_MASTER_0);
	}

	@Override
	public int getOrder() {
 		return 1;
	}
}
