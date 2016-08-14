package com.samchat.service.interfaces;

import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.user.Register_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_res;

public interface IUsersSrv {
	
	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode);
	
	public TUserUsers queryUserInfoByEmail(String email);
	
	public TUserUsers queryUserInfoByUserName(String userName);
	
	public Register_res saveRegisterUserInfo(Register_req req) throws Exception;
	
}
