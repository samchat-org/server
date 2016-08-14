package com.samchat.service.interfaces;

import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;

public interface ICommonSrv {
 	
	public List<TSysConfigs> queryAllSysconfigs();
	
	public TSysConfigs querySysconfig(String paramCode);
}
