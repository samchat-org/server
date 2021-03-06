package com.samchat.dao.db;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.beans.auto.db.entitybeans.TSysConfigsExample;
import com.samchat.common.beans.auto.db.entitybeans.TSysMessageTemplete;
import com.samchat.common.beans.auto.db.entitybeans.TSysMessageTempleteExample;
import com.samchat.common.beans.auto.db.mapper.TSysConfigsMapper;
import com.samchat.common.beans.auto.db.mapper.TSysMessageTempleteMapper;
import com.samchat.common.beans.manual.common.SysdateObjBean;
import com.samchat.common.beans.manual.db.QrySequenceVO;
import com.samchat.common.enums.Constant;
import com.samchat.dao.db.interfaces.ICommonDbDao;

@Repository
public class CommonDbDao extends BaseDbDao implements ICommonDbDao {
	
	private static final long baseMills= 1476777812155L;
	
	@Autowired
	private TSysConfigsMapper  sysConfigsMapper;
	
	@Autowired
	private TSysMessageTempleteMapper  sysMessageTempleteMapper;
	
	protected String getNamespace() {
		return "commonSqlMapper";
	}

	public List<TSysConfigs> queryAllSysconfigs(byte state) {
		TSysConfigsExample sce = new TSysConfigsExample();
		sce.createCriteria().andStateEqualTo(state);
		return sysConfigsMapper.selectByExample(sce);
	}
	
	public List<TSysConfigs> queryAllSysconfigs() {
		TSysConfigsExample sce = new TSysConfigsExample();
		sce.createCriteria().andStateNotEqualTo(Constant.SYS_PARAM_STATE.STATE_INVALID);
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
	
	public Long querySeqId(String seqName) {
		QrySequenceVO sq = new QrySequenceVO();
		sq.setSeq_name(seqName);
		insert("query_seqId", sq);
		return sq.getSeq_id();
	}

	public Timestamp querySysdate() {
		return (Timestamp) executeSqlOne("query_sysdate");
	}
	
	public SysdateObjBean querySysdateObj() throws Exception{
		String[] dateMicro = ((String)executeSqlOne("query_sysdateMicro")).split(",");
		SysdateObjBean sb = new SysdateObjBean();
		sb.setNow(new Timestamp(Constant.SDF_YYYYMMDDHHmmss.parse(dateMicro[0]).getTime()));
		sb.setNowVersion((sb.getNow().getTime() - baseMills) * 1000 + Long.parseLong(dateMicro[1]));
		return sb;
	}
	
	public List<TSysMessageTemplete> querySysMsgTplList(){
		TSysMessageTempleteExample sce = new TSysMessageTempleteExample();
		sce.createCriteria().andStateEqualTo(Constant.SYS_PARAM_STATE.STATE_VALID_IN);
		return sysMessageTempleteMapper.selectByExample(sce);
	}
}
