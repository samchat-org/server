package com.samchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.dao.db.interfaces.ICommonDbDao;
import com.samchat.service.interfaces.ICommonSrvm;

@Service
public class CommonSrvm implements ICommonSrvm{
	
	@Autowired
	private ICommonDbDao commonDbDao;
	
	public Long querySeqId(String seqName) {
		return commonDbDao.querySeqId(seqName);
	}
}
