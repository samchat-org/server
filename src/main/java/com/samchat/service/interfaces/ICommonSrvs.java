package com.samchat.service.interfaces;

import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;

public interface ICommonSrvs extends IBaseSrvs{

	public List<TSysConfigs> queryAllSysconfigs();

	public TSysConfigs querySysconfig(String paramCode);

	public List<TSysConfigs> queryAllSysconfigsForApp();

}
