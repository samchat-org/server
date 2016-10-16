package com.samchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdang.ddframe.rdb.sharding.api.HintManager;
import com.samchat.dao.db.interfaces.ITestDao;
import com.samchat.service.interfaces.ITestSrvs;
@Service
public class TestSrvs implements ITestSrvs{
	
	@Autowired
	private ITestDao testDao;

	
	public void insertDict(){
		HintManager hm = HintManager.getInstance();
		hm.setMasterRouteOnly();
		testDao.queryDict();
		System.out.print("-----------1");
 		testDao.insertDict();
 		System.out.print("-----------2");
 		
	}
	
	public void queryDict(){
		testDao.queryDict();
	}
}
