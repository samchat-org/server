package com.samchat.service.interfaces;

import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;

public interface ICommonSrvs {
 	
	public List<TSysConfigs> queryAllSysconfigs();
	
	public TSysConfigs querySysconfig(String paramCode);
	
	
}
