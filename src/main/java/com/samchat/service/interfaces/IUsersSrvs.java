package com.samchat.service.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.user.CreateSamPros_req;
import com.samchat.common.beans.auto.json.appserver.user.Login_req;
import com.samchat.common.beans.auto.json.appserver.user.Login_res;
import com.samchat.common.beans.auto.json.appserver.user.Register_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_res;
import com.samchat.common.beans.manual.common.SysdateObjBean;
import com.samchat.common.beans.manual.common.TokenBean;
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;

public interface IUsersSrvs extends IBaseSrvs {
 	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode) ;
	public TUserUsers queryUserInfoByUserName(String userName) ;
	public TUserUsers queryUserInfoByEmail(String email) ;
	public Register_res saveRegisterUserInfo(Register_req req, SysdateObjBean sysdate) throws Exception ;
	public void putRegisterCode(String countryCode, String cellPhone, String registerCode, int expireSec) ;
	public String getRegisterCode(String countryCode, String cellPhone) ;
	public String getFindpasswordVerificationCode(String countryCode, String cellPhone) ;
	public void putFindpasswordVerificationCode(String countryCode, String cellPhone, String verificationCode,int expireSec);
	public void cancelUserInfoIntoRedis(String countryCode, String cellPhone) ;
	public void niRegister(long userId, String userName, String token, Timestamp cur) throws Exception ;
	public void niTokenUpdate(long userId, String token, Timestamp cur) throws Exception ;
	public TokenMappingRds getTokenObj(String token) throws Exception ;
	public void deleteToken(String token) ;
	public void updateToken(String token, TokenMappingRds tokenObj) throws Exception;
	public TUserProUsers saveProsUserInfo(CreateSamPros_req req, TUserUsers user, SysdateObjBean sysdate) throws Exception ;
	public void updatePassword(long userId, String password, Timestamp sysdate) ;
	public TUserProUsers queryProUser(long userId) ;
	public void loginPwderrorCheck(String countryCode, String cellphone, Timestamp sysdate) throws Exception ;
	public List<TUserUsers> queryUsers() ;
	public TUserUsers queryUser(long userId) ;
	public List<QryUserInfoVO> queryUsersFuzzy(String key) ;
	public List<QryUserInfoVO> queryUserAccurate(Long type, String cellphone, String userName, String userId) ;
	public List<QryUserInfoVO> queryUsersGroup(List<Long> userIds) ;
	public List<TUserUsers> queryUserWithoutToken(long type, String countrycode, String cellphone, String userName) ;
	public TUserUsers updateAvatar(String origin, String thumb, long userId, Timestamp sysdate) throws Exception ;
	public void updateDbToken(long userId, String token);
	public TUserProUsers updateTokenInfo(TUserUsers user, SysdateObjBean sysdate, String realToken) throws Exception;
	public Login_res saveLoginUserInfo(Login_req req, TUserUsers user, SysdateObjBean sysdate)  throws Exception;
}
