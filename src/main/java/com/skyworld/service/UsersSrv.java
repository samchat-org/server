package com.skyworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyworld.beans.auto.db.entitybeans.TUserUsers;
import com.skyworld.beans.auto.db.mapper.TUserUsersMapper;
import com.skyworld.service.interfaces.IUsersSrv;

@Service
public class UsersSrv extends BaseSrv implements IUsersSrv {
	
	@Autowired
	private TUserUsersMapper userUsersMapper;

	public TUserUsers qryUserInfo(String phoneNo, String email) {
		return userUsersMapper.selectByPrimaryKey(new Long(1));
  	}
}
