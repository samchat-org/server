package com.samchat.common.thread;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.enums.cache.CacheNameCacheEnum;
import com.samchat.service.interfaces.ICommonSrvs;

public class SysConfigRefreshThread extends ConfigRefresh<TSysConfigs> {

	@Autowired
	private ICommonSrvs commonSrv;
	
	public SysConfigRefreshThread(){
	}
	
	public SysConfigRefreshThread(ICommonSrvs commonSrv){
		this.commonSrv = commonSrv;
	}
	
	public List<TSysConfigs> queryCfgList(){
		return commonSrv.queryAllSysconfigs();
	}
	
	public String getParamCode(TSysConfigs cfg){
		return cfg.getParam_code();
	}
	
	public String getParamValue(TSysConfigs cfg){
		return cfg.getParam_value();
	}
	
	public String getEcacheVal(){
		return CacheNameCacheEnum.ECH_SYS_CONFIG.val();
	}	
}
