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
import com.samchat.common.beans.auto.db.entitybeans.TUserUsersExample;
import com.samchat.common.beans.auto.json.appserver.user.CreateSamPros_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.utils.CacheUtil;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.Md5Util;
import com.samchat.common.utils.niUtils.NiUtil;
import com.samchat.dao.db.interfaces.IUserDbDao;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IUsersSrv;

@Service
public class UsersSrv extends BaseSrv implements IUsersSrv {

	private static Logger log = Logger.getLogger(UsersSrv.class);

	@Autowired
	private IUserRedisDao<String, Object> userRedisDao;

	@Autowired
	private IUserDbDao userDbDao;

	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode) {
		return userDbDao.queryUserInfoByPhone(phoneNo, countryCode);
	}

	public TUserUsers queryUserInfoByUserName(String userName) {
		return userDbDao.queryUserInfoByUserName(userName);
	}

	public TUserUsers queryUserInfoByEmail(String email) {
		return userDbDao.queryUserInfoByEmail(email);
	}

	public Register_res saveRegisterUserInfo(Register_req req) throws Exception {

		Timestamp sysdate = querySysdate();
		long time = sysdate.getTime();

		Register_req.Body body = req.getBody();
		String cellPhone = body.getCellphone();
		String countryCode = body.getCountrycode();
		String userName = body.getUsername();
		String pwd = Md5Util.getSign4String(body.getPwd(), "");
		String deviceId = body.getDeviceid();

		String token = getAddedToken(countryCode, cellPhone, time, deviceId);

		TUserUsers uu = new TUserUsers();

		uu.setUser_type(Constant.USER_TYPE_CUSTOMER);
		uu.setPhone_no(cellPhone);
		uu.setCountry_code(countryCode);
		uu.setUser_name(userName);
		uu.setUser_pwd(pwd);
		uu.setState(Constant.STATE_IN_USE);
		uu.setState_date(sysdate);
		uu.setCreate_date(sysdate);
		uu.setCur_device_id(deviceId);
		uu.setCur_token(token);
 		userDbDao.insertUser(uu);

 		TUserUsers u = userDbDao.queryUserInfoByPhone(cellPhone, countryCode);
		if (u == null) {
			throw new RuntimeException("select register info failed, phoneNo:" + cellPhone + "--countryCode:"
					+ countryCode);
		}
		
		niRegister(u.getUser_id(), userName, token, deviceId, sysdate);

		Register_res res = new Register_res();
		res.setRet(Constant.SUCCESS);

		res.setToken(token);
		Register_res.User user = new Register_res.User();
		user.setId(u.getUser_id());
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

	public String getAddedToken(String countryCode, String cellPhone, long time, String deviceId) throws Exception {

		TokenRds tk = new TokenRds();
		tk.setCountryCode(countryCode);
		tk.setCellPhone(cellPhone);
		tk.setDeviceId(deviceId);
		long expireSec = CommonUtil.getSysConfigInt("token_failure_interval");
		for (int i = 0;; i++) {
			String token = Md5Util.getSign4String(countryCode + "_" + cellPhone + "_" + time + "_" + deviceId + i, "");
			if (userRedisDao.setNX(CacheUtil.getTokenCacheKey(token + deviceId), tk, expireSec)) {
				return token;
			}
		}
	}

	public void niRegister(long userId, String userName, String token, String deviceId, Timestamp cur) throws Exception {
		Map<String, String> register = new HashMap<String, String>();
		register.put("accid", String.valueOf(userId));
		register.put("name", userName);
		register.put("token", token + deviceId);
		NiUtil.createAction(register, cur);
	}

	public void niTokenUpdate(long userId, String token, String deviceId, Timestamp cur) throws Exception {
		Map<String, String> update = new HashMap<String, String>();
		update.put("accid", String.valueOf(userId));
		update.put("token", token + deviceId);
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

	public TUserProUsers saveProsUserInfo(CreateSamPros_req req, TUserUsers user) {

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
		if(info != null){
			proUsers.setLongitude(info.getLongitude());
			proUsers.setLatitude(info.getLatitude());
		}
		proUsers.setPlace_id(location.getPlace_id());
		proUsers.setAddress(location.getAddress());
		proUsers.setState(Constant.STATE_IN_USE);

		Timestamp cur = querySysdate();
		proUsers.setState_date(cur);
		proUsers.setCreate_date(cur);
 		userDbDao.insertProUser(proUsers);

		TUserUsers userCon = new TUserUsers();
		userCon.setUser_id(user.getUser_id());
		userCon.setUser_type(Constant.USER_TYPE_SERVICES);
		userCon.setState_date(cur);
 		userDbDao.updateUser(userCon);
 
		return proUsers;

	}

	public void updatePassword(long userId, String password) {
		TUserUsers userCon = new TUserUsers();
		userCon.setUser_id(userId);
		userCon.setUser_pwd(password);
		userCon.setState_date(querySysdate());
		userDbDao.updateUser(userCon);
	}
}
