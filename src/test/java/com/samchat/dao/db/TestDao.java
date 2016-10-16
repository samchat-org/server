package com.samchat.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.common.beans.auto.db.entitybeans.TSysDictInfo;
import com.samchat.common.beans.auto.db.mapper.TSysDictInfoMapper;
import com.samchat.dao.db.interfaces.ITestDao;

@Repository
public class TestDao extends BaseDbDao implements ITestDao {
	
	@Autowired
	private TSysDictInfoMapper sysDictInfoMapper;
	
	protected String getNamespace() {
		return "testSqlMapper";
	}
	
	public void insertDict(){
		TSysDictInfo record = new TSysDictInfo();
		record.setDict_id((int)(System.currentTimeMillis()/10000));
		sysDictInfoMapper.insert(record);
	}
	
	public void queryDict(){
		TSysDictInfo record = new TSysDictInfo();
		record.setDict_code("1");
		sysDictInfoMapper.selectByPrimaryKey(1);
	}
}
