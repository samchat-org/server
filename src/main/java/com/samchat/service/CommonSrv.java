package com.samchat.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.beans.auto.db.entitybeans.TSysConfigsExample;
import com.samchat.common.beans.auto.db.mapper.TSysConfigsMapper;
import com.samchat.dao.db.interfaces.ICommonDbDao;
import com.samchat.service.interfaces.ICommonSrv;

@Service
public class CommonSrv implements ICommonSrv {

	@Autowired
	private ICommonDbDao commonDbDao;

	public List<TSysConfigs> queryAllSysconfigs() {
		return commonDbDao.queryAllSysconfigs();
	}

	public TSysConfigs querySysconfig(String paramCode) {
		return commonDbDao.querySysconfig(paramCode);
	}

	public Timestamp querySysdate() {
		return commonDbDao.querySysdate();
	}

}
