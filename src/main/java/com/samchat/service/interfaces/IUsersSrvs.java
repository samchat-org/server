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
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;

public interface IUsersSrvs extends IBaseSrvs {
 	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode) ;
	public TUserUsers queryUserInfoByUserName(String userName) ;
	public TUserUsers queryUserInfoByEmail(String email) ;
	public Register_res saveRegisterUserInfo_master(Register_req req, SysdateObjBean sysdate) throws Exception ;
	public void putRegisterCode(String countryCode, String cellPhone, String registerCode) ;
	public String getRegisterCode(String countryCode, String cellPhone) ;
	public String getFindpasswordVerificationCode(String countryCode, String cellPhone) ;
	public void putFindpasswordVerificationCode(String countryCode, String cellPhone, String verificationCode);
	public void cancelUserInfoIntoRedis(String countryCode, String cellPhone) ;
	public void niRegister(String userId, String userName, String token, Timestamp cur) throws Exception ;
	public void niTokenUpdate(long userId, String token, Timestamp cur) throws Exception ;
	public TokenMappingRds getTokenObj(String token) throws Exception ;
	public void deleteRedisToken(String token) ;
 	public TUserProUsers saveProsUserInfo_master(CreateSamPros_req req, TUserUsers user, SysdateObjBean sysdate) throws Exception ;
	public void updatePassword(long userId, String password, Timestamp sysdate) ;
	public TUserProUsers queryProUser(long userId) ;
	public void loginPwderrorCheck(String countryCode, String cellphone, Timestamp sysdate) throws Exception ;
	public List<TUserUsers> queryUsers() ;
	public TUserUsers queryUser(long userId) ;
	public List<QryUserInfoVO> queryUsersFuzzy(String key, long count, long type) ;
	public List<QryUserInfoVO> queryUserAccurate(Long type, String cellphone, String userName, String userId) ;
	public List<QryUserInfoVO> queryUsersGroup(List<Long> userIds) ;
	public List<TUserUsers> queryUserWithoutToken(long type, String countrycode, String cellphone, String userName) ;
	public TUserUsers updateAvatar(String origin, String thumb, long userId, SysdateObjBean sysdate) throws Exception ;
	public void updateDbToken(long userId, String token);
	public TUserProUsers cacheBaseUserInfoUpdate(TUserUsers user, SysdateObjBean sysdate, String realToken) throws Exception;
	public Login_res saveLoginUserInfo_master(Login_req req, TUserUsers user, SysdateObjBean sysdate)  throws Exception;
	public void updateUser(TUserUsers userCon);
	public void deleteRedisUserInfo(long userId);
	public TUserUsers queryUserInfoByPhone_master(String phoneNo, String countryCode);
	public TUserUsers queryUserInfoByUserName_master(String userName);
	public TUserUsers queryUser_master(long userId);
	public TUserUsers updateAvatar_master(String origin, String thumb, long userId, SysdateObjBean sysdate)  throws Exception;
	public void putRegisterCodeCtrl(String countryCode, String cellPhone);
	public String getRegisterCodeCtrl(String countryCode, String cellPhone);
	public void putFindpasswordVerificationCodeCtrl(String countryCode, String cellPhone);
	public String getFindpasswordVerificationCodeCtrl(String countryCode, String cellPhone);

}
