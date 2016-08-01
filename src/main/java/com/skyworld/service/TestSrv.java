package com.skyworld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyworld.beans.auto.db.entitybeans.TbFrmDictInfoExample;
import com.skyworld.beans.auto.db.mapper.TbFrmDictInfoMapper;
import com.skyworld.service.interfaces.ITestSrv;

@Service
public class TestSrv extends BaseSrv implements ITestSrv {
	
	@Autowired
	private TbFrmDictInfoMapper tbFrmDictInfoMapper;

	public void select() {
		TbFrmDictInfoExample fe = new TbFrmDictInfoExample();
		TbFrmDictInfoExample.Criteria  c = fe.createCriteria();
		c.andDict_codeEqualTo("1");
		List list = tbFrmDictInfoMapper.selectByExample(fe);
		System.out.print(123);
	}
}
