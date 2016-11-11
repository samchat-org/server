package com.samchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.beans.auto.db.entitybeans.TSysMessageTemplete;
import com.samchat.common.enums.Constant;
import com.samchat.dao.db.interfaces.ICommonDbDao;
import com.samchat.service.interfaces.ICommonSrvs;

@Service
public class CommonSrvs extends BaseSrvs implements ICommonSrvs {

	@Autowired
	private ICommonDbDao commonDbDao;

	public List<TSysConfigs> queryAllSysconfigs() {
		return commonDbDao.queryAllSysconfigs();
	}

	public TSysConfigs querySysconfig(String paramCode) {
		return commonDbDao.querySysconfig(paramCode);
	}
	
	public List<TSysConfigs> queryAllSysconfigsForApp() {
		return commonDbDao.queryAllSysconfigs(Constant.SYS_PARAM_STATE.STATE_VALID_OUT);
	}
	
	public List<TSysMessageTemplete> querySysMsgTplList(){
		return this.commonDbDao.querySysMsgTplList();
	}
}
