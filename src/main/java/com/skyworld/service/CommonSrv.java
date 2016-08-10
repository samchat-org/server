package com.skyworld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyworld.beans.auto.db.entitybeans.TSysConfigs;
import com.skyworld.beans.auto.db.entitybeans.TSysConfigsExample;
import com.skyworld.beans.auto.db.mapper.TSysConfigsMapper;
import com.skyworld.common.Constant;
import com.skyworld.service.interfaces.ICommonSrv;

@Service
public class CommonSrv extends BaseSrv implements ICommonSrv {
	
	@Autowired
	private TSysConfigsMapper  sysConfigsMapper;
	
	public List<TSysConfigs> queryAllSysconfigs() {
		TSysConfigsExample sce = new TSysConfigsExample();
		sce.createCriteria().andStateEqualTo(Constant.STATE_IN_USE);
		return sysConfigsMapper.selectByExample(sce);
	}
	
	public TSysConfigs querySysconfig(String paramCode){
		TSysConfigsExample sce = new TSysConfigsExample();
		sce.createCriteria().andParam_codeEqualTo(paramCode).andStateEqualTo(Constant.STATE_IN_USE);
		List<TSysConfigs> cfgs = sysConfigsMapper.selectByExample(sce);
		if(cfgs.size() == 0)
			return null;
		return cfgs.get(0);
	}
	
} 
