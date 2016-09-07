package com.samchat.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.user.CreateSamPros_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_res;
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.beans.manual.json.redis.LoginErrRds;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.utils.CacheUtil;
import com.samchat.common.utils.Md5Util;
import com.samchat.common.utils.niUtils.NiUtil;
import com.samchat.dao.db.interfaces.ICommonDbDao;
import com.samchat.dao.db.interfaces.IUserDbDao;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IUsersSrvs;

@Service
public class UsersSrvs implements IUsersSrvs {

	private static Logger log = Logger.getLogger(UsersSrvs.class);

	@Autowired
	private IUserRedisDao<String, Object> userRedisDao;
	@Autowired
	private IUserDbDao userDbDao;
	@Autowired
	private ICommonDbDao commonDbDao;

	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode) {
		return userDbDao.queryUserInfoByPhone(phoneNo, countryCode);
	}

	public TUserUsers queryUserInfoByUserName(String userName) {
		return userDbDao.queryUserInfoByUserName(userName);
	}

	public TUserUsers queryUserInfoByEmail(String email) {
		return userDbDao.queryUserInfoByEmail(email);
	}

	public Register_res saveRegisterUserInfo(Register_req req, Timestamp sysdate) throws Exception {

		long time = sysdate.getTime();
		Register_req.Body body = req.getBody();
		String cellPhone = body.getCellphone();
		String countryCode = body.getCountrycode();
		String userName = body.getUsername();
		String pwd = Md5Util.getSign4String(body.getPwd(), "");
		String deviceId = body.getDeviceid();

		TUserUsers uu = new TUserUsers();

		uu.setUser_type(Constant.USER_TYPE_CUSTOMER);
		uu.setPhone_no(cellPhone);
		uu.setCountry_code(countryCode);
		uu.setUser_name(userName);
		uu.setUser_pwd(pwd);
		uu.setState(Constant.STATE_IN_USE);
		uu.setState_date(sysdate);
		uu.setCreate_date(sysdate);
		userDbDao.insertUser(uu, sysdate);

		String[] token = getAddedToken(countryCode, cellPhone, time, deviceId, uu.getUser_id(), uu.getUser_type());
		String retToken = token[0];
		String realToken = token[1];
		niRegister(uu.getUser_id(), userName, realToken, sysdate);

		Register_res res = new Register_res();
		res.setRet(Constant.SUCCESS);

		res.setToken(retToken);
		Register_res.User user = new Register_res.User();
		user.setId(uu.getUser_id());
		user.setLastupdate(time);
		res.setUser(user);
		return res;
	}

	public void putRegisterCode(String countryCode, String cellPhone, String registerCode, long expireSec) {
		String keystr = CacheUtil.getRegiserCodeCacheKey(countryCode, cellPhone);
		userRedisDao.set(keystr, registerCode, expireSec);
	}

	public String getRegisterCode(String countryCode, String cellPhone) {
		String keystr = CacheUtil.getRegiserCodeCacheKey(countryCode, cellPhone);
		return userRedisDao.get(keystr);
	}

	public String getFindpasswordVerificationCode(String countryCode, String cellPhone) {
		String keystr = CacheUtil.getFindpasswordCacheKey(countryCode, cellPhone);
		return userRedisDao.get(keystr);
	}

	public void putFindpasswordVerificationCode(String countryCode, String cellPhone, String verificationCode,
			long expireSec) {
		String keystr = CacheUtil.getFindpasswordCacheKey(countryCode, cellPhone);
		userRedisDao.set(keystr, verificationCode, expireSec);
	}

	public String[] getAddedToken(String countryCode, String cellPhone, long time, String deviceId, long userId,
			long userType) throws Exception {

		TokenRds tk = new TokenRds();
		tk.setUserId(userId);
		tk.setCountryCode(countryCode);
		tk.setCellPhone(cellPhone);
		tk.setUserType(userType);
		tk.setDeviceId(deviceId);
		for (int i = 0;; i++) {
			String retToken = Md5Util.getSign4String(countryCode + "_" + cellPhone + "_" + time + "_" + deviceId + i);
			String realToken = CacheUtil.getRealToken(retToken, deviceId);
			if (userRedisDao.setNX(CacheUtil.getTokenCacheKey(realToken), tk, 0)) {
				String[] ret = new String[2];
				ret[0] = retToken;
				ret[1] = realToken;
				return ret;
			}
		}
	}

	public void resetToken(String userType, String countryCode, String cellPhone, String realToken) {
		String key = CacheUtil.getTokenCacheKey(realToken);
		TokenRds tk = userRedisDao.getJsonObj(key);
		if (tk == null) {
			return;
		}
		if (userType != null) {
			tk.setUserType(Long.parseLong(userType));
		}
		if (countryCode != null) {
			tk.setCountryCode(countryCode);
		}
		if (cellPhone != null) {
			tk.setCellPhone(cellPhone);
		}
		userRedisDao.set(key, tk, 0);

	}

	public void cancelUserInfoIntoRedis(String countryCode, String cellPhone) {
		String key = CacheUtil.getUserInfoCacheKey(countryCode, cellPhone);
		userRedisDao.delete(key);
	}

	public void setUserInfoIntoRedis(String countryCode, String cellPhone, String token) {
		UserInfoRds uif = new UserInfoRds();
		uif.setToken(token);
		String key = CacheUtil.getUserInfoCacheKey(countryCode, cellPhone);
		userRedisDao.set(key, uif, 0);
	}

	public UserInfoRds getUserInfoIntoRedis(String countryCode, String cellPhone) {
		String key = CacheUtil.getUserInfoCacheKey(countryCode, cellPhone);
		return (UserInfoRds) userRedisDao.getJsonObj(key);
	}

	public void niRegister(long userId, String userName, String token, Timestamp cur) throws Exception {
		Map<String, String> register = new HashMap<String, String>();
		register.put("accid", String.valueOf(userId));
		register.put("name", userName);
		register.put("token", token);
		NiUtil.createAction(register, cur);
	}

	public void niTokenUpdate(long userId, String token, Timestamp cur) throws Exception {
		Map<String, String> update = new HashMap<String, String>();
		update.put("accid", String.valueOf(userId));
		update.put("token", token);
		NiUtil.updateTokenAction(update, cur);
	}

	public TokenRds getTokenObj(String token) {
		String key = CacheUtil.getTokenCacheKey(token);
		return userRedisDao.getJsonObj(key);
	}

	public void deleteToken(String token) {
		String key = CacheUtil.getTokenCacheKey(token);
		userRedisDao.delete(key);
	}

	public TUserProUsers saveProsUserInfo(CreateSamPros_req req, TUserUsers user, Timestamp sysdate) {

		CreateSamPros_req.Body body = req.getBody();

		TUserProUsers proUsers = new TUserProUsers();
		proUsers.setUser_id(user.getUser_id());
		proUsers.setCompany_name(body.getCompany_name());
		proUsers.setService_category(body.getService_category());
		proUsers.setService_description(body.getService_description());
		proUsers.setCountry_code(body.getCountrycode());
		proUsers.setPhone_no(body.getPhone());
		proUsers.setEmail(body.getEmail());

		CreateSamPros_req.Location location = body.getLocation();
		CreateSamPros_req.Location_info info = location.getLocation_info();
		if (info != null) {
			proUsers.setLongitude(info.getLongitude());
			proUsers.setLatitude(info.getLatitude());
		}
		proUsers.setPlace_id(location.getPlace_id());
		proUsers.setAddress(location.getAddress());
		proUsers.setState(Constant.STATE_IN_USE);

		proUsers.setState_date(sysdate);
		proUsers.setCreate_date(sysdate);
		userDbDao.insertProUser(proUsers, sysdate);

		TUserUsers userCon = new TUserUsers();
		userCon.setUser_id(user.getUser_id());
		userCon.setUser_type(Constant.USER_TYPE_SERVICES);
		userCon.setState_date(sysdate);
		userDbDao.updateUser(userCon, sysdate);

		return proUsers;

	}

	public void updatePassword(long userId, String password, Timestamp sysdate) {
		TUserUsers userCon = new TUserUsers();
		userCon.setUser_id(userId);
		userCon.setUser_pwd(password);

		userCon.setState_date(sysdate);
		userDbDao.updateUser(userCon, sysdate);
	}

	public TUserProUsers queryProUser(long userId) {
		return userDbDao.queryProUser(userId);
	}

	public void loginPwderrorCheck(String countryCode, String cellphone, Timestamp sysdate) {
		LoginErrRds loginerr = this.userRedisDao.getJsonObj(Constant.CACHE_NAME.LOGIN_ERR + ":" + countryCode + "_"
				+ cellphone);
		if (loginerr == null) {
			loginerr = new LoginErrRds();
			loginerr.setFirst(sysdate.getTime());
		}
		loginerr.setTime(loginerr.getTime() + 1);

	}

	public List<TUserUsers> queryUsers() {
		return userDbDao.queryUsers();
	}

	public TUserUsers queryUser(long userId) {
		return userDbDao.queryUser(userId);
	}

	public List<QryUserInfoVO> queryUsersFuzzy(String key) {
		return userDbDao.queryUsersFuzzy(key);
	}

	public List<QryUserInfoVO> queryUserAccurate(Long type, String cellphone, String userName, String userId) {

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("cellphone", cellphone);
		param.put("user_id", userId);
		param.put("user_name", userName);
		param.put("type", type);
		return userDbDao.queryUserAccurate(param);
	}

	public List<QryUserInfoVO> queryUsersGroup(List<Long> userIds) {
		return userDbDao.queryUsersGroup(userIds);
	}

	public List<TUserUsers> queryUserWithoutToken(long type, String countrycode, String cellphone, String userName) {
		return userDbDao.queryUserWithoutToken(type, countrycode, cellphone, userName);
	}

	public long updateAvatar(String origin, String thumb, long userId, Timestamp sysdate) {
		TUserUsers u = new TUserUsers();
		u.setUser_id(userId);
		u.setAvatar_origin(origin);
		u.setAvatar_thumb(thumb);
		userDbDao.updateUser(u, sysdate);
		return u.getState_date().getTime();
	}

}
