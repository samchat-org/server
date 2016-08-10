package com.skyworld.service.interfaces;

import java.util.List;

import com.skyworld.beans.auto.db.entitybeans.TSysConfigs;

public interface ICommonSrv {
 	
	public List<TSysConfigs> queryAllSysconfigs();
	
	public TSysConfigs querySysconfig(String paramCode);
}
