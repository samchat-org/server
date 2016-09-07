package com.samchat.dao.db.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.beans.manual.db.QrySequenceVO;

public interface ICommonDbDao extends IBaseDbDao {

	public List<TSysConfigs> queryAllSysconfigs();

	public TSysConfigs querySysconfig(String paramCode);

	public Long querySeqId(String seqName);

	public Timestamp querySysdate();

}
