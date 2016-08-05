package com.skyworld.service.interfaces;

import com.skyworld.beans.auto.db.entitybeans.TUserUsers;

public interface IUsersSrv {
	
	public TUserUsers qryUserInfo(String phoneNo, String email);
	
}
