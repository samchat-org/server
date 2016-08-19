package com.samchat.service.interfaces;

import java.sql.Timestamp;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.user.CreateSamPros_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;

public interface IUsersSrv extends IBaseSrv {

	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode);

	public TUserUsers queryUserInfoByEmail(String email);

	public TUserUsers queryUserInfoByUserName(String userName);

	public Register_res saveRegisterUserInfo(Register_req req) throws Exception;

	public void putRegisterCode(String countryCode, String cellPhone, String registerCode, long expireSec);

	public String getRegisterCode(String countryCode, String cellPhone);

	public String getAddedToken(String countryCode, String cellPhone, long time, String deviceId) throws Exception;

	public void niRegister(long userId, String userName, String token, String deviceId, Timestamp cur) throws Exception;
	
	public void niTokenUpdate(long userId, String token, String deviceId, Timestamp cur) throws Exception;
	
	public TokenRds getTokenObj(String token);
	
	public void deleteToken(String token);
	
	public TUserProUsers saveProsUserInfo(CreateSamPros_req req, TUserUsers user);
	
	public String getFindpasswordVerificationCode(String countryCode, String cellPhone);
	
	public void putFindpasswordVerificationCode(String countryCode, String cellPhone, String verificationCode, long expireSec);
	
	public void updatePassword(long userId, String password);

}
