package com.samchat.common.thread;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TSysMessageTemplete;
import com.samchat.common.enums.cache.CacheNameCacheEnum;
import com.samchat.service.interfaces.ICommonSrvs;

public class SysMsgTplRefreshThread extends ConfigRefresh<TSysMessageTemplete> {

	@Autowired
	private ICommonSrvs commonSrv;

	public SysMsgTplRefreshThread() {
	}

	public SysMsgTplRefreshThread(ICommonSrvs commonSrv) {
		this.commonSrv = commonSrv;
	}

	public List<TSysMessageTemplete> queryCfgList() {
		return commonSrv.querySysMsgTplList();
	}

	public String getParamCode(TSysMessageTemplete cfg) {
		return cfg.getAction_code();
	}

	public String getParamValue(TSysMessageTemplete cfg) {
		return cfg.getTemplete();
	}

	public String getEcacheVal() {
		return CacheNameCacheEnum.ECH_SYS_MSG_TPL.val();
	}
}
