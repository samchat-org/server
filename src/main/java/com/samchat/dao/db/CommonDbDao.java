package com.samchat.dao.db;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.beans.auto.db.entitybeans.TSysConfigsExample;
import com.samchat.common.beans.auto.db.mapper.TSysConfigsMapper;
import com.samchat.common.beans.manual.db.QrySequenceVO;
import com.samchat.dao.db.interfaces.ICommonDbDao;

@Repository
public class CommonDbDao extends BaseDbDao implements ICommonDbDao {
	
	@Autowired
	private TSysConfigsMapper  sysConfigsMapper;
	
	protected String getNamespace() {
		return "commonSqlMapper";
	}

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
