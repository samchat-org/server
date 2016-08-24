package com.samchat.dao.db.interfaces;

import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;

public interface ICommonDbDao extends IBaseDbDao {
	
	public List<TSysConfigs> queryAllSysconfigs();
	
	public TSysConfigs querySysconfig(String paramCode);
	
 }
