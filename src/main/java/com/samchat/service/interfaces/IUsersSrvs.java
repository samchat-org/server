package com.samchat.service.interfaces;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.user.CreateSamPros_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_res;
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;

public interface IUsersSrvs {
	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode);

	public TUserUsers queryUserInfoByUserName(String userName);

	public TUserUsers queryUserInfoByEmail(String email);

	public Register_res saveRegisterUserInfo(Register_req req, Timestamp sysdate) throws Exception;

	public void putRegisterCode(String countryCode, String cellPhone, String registerCode, long expireSec);

	public String getRegisterCode(String countryCode, String cellPhone);

	public String getFindpasswordVerificationCode(String countryCode, String cellPhone);

	public void putFindpasswordVerificationCode(String countryCode, String cellPhone, String verificationCode,
			long expireSec);

	public String[] getAddedToken(String countryCode, String cellPhone, long time, String deviceId, long userId,
			long userType) throws Exception;

	public void resetToken(String userType, String countryCode, String cellPhone, String realToken);

	public void cancelUserInfoIntoRedis(String countryCode, String cellPhone);

	public void setUserInfoIntoRedis(String countryCode, String cellPhone, String token);

	public UserInfoRds getUserInfoIntoRedis(String countryCode, String cellPhone);

	public void niRegister(long userId, String userName, String token, Timestamp cur) throws Exception;

	public void niTokenUpdate(long userId, String token, Timestamp cur) throws Exception;

	public TokenRds getTokenObj(String token);

	public void deleteToken(String token);

	public TUserProUsers saveProsUserInfo(CreateSamPros_req req, TUserUsers user, Timestamp sysdate);

	public void updatePassword(long userId, String password, Timestamp sysdate);

	public TUserProUsers queryProUser(long userId);

	public void loginPwderrorCheck(String countryCode, String cellphone, Timestamp sysdate);

	public List<TUserUsers> queryUsers();

	public TUserUsers queryUser(long userId);

	public List<QryUserInfoVO> queryUsersFuzzy(String key);

	public List<QryUserInfoVO> queryUserAccurate(Long type, String cellphone, String userName, String userId);

	public List<QryUserInfoVO> queryUsersGroup(List<Long> userIds);

	public List<TUserUsers> queryUserWithoutToken(long type, String countrycode, String cellphone, String userName);

	public long updateAvatar(String origin, String thumb, long userId, Timestamp sysdate);

}
