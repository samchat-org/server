package com.samchat.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdang.ddframe.rdb.sharding.api.HintManager;
import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.user.CreateSamPros_req;
import com.samchat.common.beans.auto.json.appserver.user.Login_req;
import com.samchat.common.beans.auto.json.appserver.user.Login_res;
import com.samchat.common.beans.auto.json.appserver.user.Login_res.Sam_pros_info;
import com.samchat.common.beans.auto.json.appserver.user.Register_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_res;
import com.samchat.common.beans.auto.json.ni.id.Create_req;
import com.samchat.common.beans.auto.json.ni.id.Update_req;
import com.samchat.common.beans.manual.common.SysdateObjBean;
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.beans.manual.json.redis.LoginErrRds;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.redis.TokenValRds;
import com.samchat.common.beans.manual.json.redis.UserInfoProRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.cache.CacheNameCacheEnum;
import com.samchat.common.exceptions.RedisException;
import com.samchat.common.utils.CacheUtil;
import com.samchat.common.utils.Md5Util;
import com.samchat.common.utils.S3Util;
import com.samchat.common.utils.niUtils.NiUtil;
import com.samchat.dao.db.interfaces.ICommonDbDao;
import com.samchat.dao.db.interfaces.IContactDbDao;
import com.samchat.dao.db.interfaces.IOfficialAccountDbDao;
import com.samchat.dao.db.interfaces.IUserDbDao;
import com.samchat.dao.redis.interfaces.IUserRedisDao;
import com.samchat.service.interfaces.IUsersSrvs;

@Service
public class UsersSrvs extends BaseSrvs implements IUsersSrvs {

	private static Logger log = Logger.getLogger(UsersSrvs.class);

	@Autowired
	private IUserRedisDao<String, Object> userRedisDao;
	@Autowired
	private IUserDbDao userDbDao;
	@Autowired
	private ICommonDbDao commonDbDao;
	@Autowired
	private IOfficialAccountDbDao officialAccountDbDao;
	@Autowired
	private IContactDbDao contactDbDao;

	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode) {
		return userDbDao.queryUserInfoByPhone(phoneNo, countryCode);
	}
	
	public TUserUsers queryUserInfoByPhone_master(String phoneNo, String countryCode) {
		return queryUserInfoByPhone(phoneNo, countryCode);
	}

	public TUserUsers queryUserInfoByUserName(String userName) {
		return userDbDao.queryUserInfoByUserName(userName);
	}
	
	public TUserUsers queryUserInfoByUserName_master(String userName) {
		return queryUserInfoByUserName(userName);
	}

	public TUserUsers queryUserInfoByEmail(String email) {
		return userDbDao.queryUserInfoByEmail(email);
	}
	
	private String getStateDate(Timestamp stateDate){
		if(stateDate == null){
			return "0";
		}else{
			return stateDate.getTime() + "";
		}
	}
	
	public void cacheExtUserInfoUpdate(long userId){
		Timestamp fsd = officialAccountDbDao.queryFollowListStateDate(userId);
		hsetUserInfoFollowListDate(userId, getStateDate(fsd));
		
		Timestamp csd = contactDbDao.queryContactListStateDate(userId);
		hsetUserInfoServicerListDate(userId, getStateDate(csd));
		
		Timestamp cpsd = contactDbDao.queryContactProsListStateDate(userId);
		hsetUserInfoCustomerListDate(userId, getStateDate(cpsd));
		
	}

	public TUserProUsers cacheBaseUserInfoUpdate(TUserUsers user, SysdateObjBean sysdate, String realToken) throws Exception {

		long nowVersion = sysdate.getNowVersion();
		long userId = user.getUser_id();
		
		TokenValRds token = hgetUserInfoTokenJsonObj(userId);
		if (token != null) {
			deleteRedisToken(token.getToken());
		}
		
		TokenMappingRds tk = new TokenMappingRds();
		tk.setUserId(userId);
		tk.setNowVersion(nowVersion);
		setTokenJsonObj(realToken, tk);
		
		UserInfoRds uur = new UserInfoRds();
		uur.setNowVersion(nowVersion);
		PropertyUtils.copyProperties(uur, user);
		hsetUserInfoJsonObj(userId, uur);

		TokenValRds tvr = new TokenValRds();
		tvr.setToken(realToken);
		tvr.setNowVersion(nowVersion);
		hsetUserInfoTokenJsonObj(userId, tvr);

		TUserProUsers uup = userDbDao.queryProUser(userId);
		if (uup != null) {
			UserInfoProRds uupr = new UserInfoProRds();
			uupr.setNowVersion(nowVersion);
			PropertyUtils.copyProperties(uupr, uup);
			hsetUserInfoProsJsonObj(userId, uupr);
		}
		return uup;

	}

	private void setTokenJsonObj(String realTokenKey, TokenMappingRds tk) {
		try {
			userRedisDao.setJsonObj(CacheUtil.getTokenCacheKey(realTokenKey), tk);
		} catch (Exception e) {
			throw new RedisException("", e);
		}
	}
	
	public void updateUser(TUserUsers userCon){
		userDbDao.updateUser(userCon);
	}

	public Login_res saveLoginUserInfo_master(Login_req req, TUserUsers user, SysdateObjBean sysdate) throws Exception {
		
 		Login_req.Body body = req.getBody();
		String deviceId = body.getDeviceid();
		long userId = user.getUser_id();

		String retToken = CacheUtil.getToken(user.getCountry_code(), user.getPhone_no(), sysdate.getNowVersion(),
				deviceId);
		String realToken = CacheUtil.getRealToken(retToken, deviceId);
		// update t_user_users.cur_token
		TUserUsers userCon = new TUserUsers();
		userCon.setUser_id(userId);
		userCon.setCur_token(realToken);
		userDbDao.updateUser(userCon);

		TUserProUsers proUser = cacheBaseUserInfoUpdate(user, sysdate, realToken);
		cacheExtUserInfoUpdate(userId);
		niTokenUpdate(userId, realToken, sysdate.getNow());
		
		Login_res res = new Login_res();
		res.setToken(retToken);

		Login_res.User userRes = new Login_res.User();
		res.setUser(userRes);

		userRes.setId(userId);
		userRes.setUsername(user.getUser_name());
		userRes.setType(user.getUser_type());
		userRes.setCountrycode(user.getCountry_code());
		userRes.setCellphone(user.getPhone_no());
		userRes.setAddress(user.getAddress());
		userRes.setEmail(user.getEmail());
		userRes.setLastupdate(user.getState_date().getTime());

		Login_res.Avatar avatar = new Login_res.Avatar();
		avatar.setOrigin(user.getAvatar_origin());
		avatar.setThumb(user.getAvatar_thumb());
		userRes.setAvatar(avatar);

		Sam_pros_info info = new Login_res.Sam_pros_info();
		userRes.setSam_pros_info(info);
		if (user.getUser_type() == Constant.USER_TYPE_SERVICES) {
			if (proUser != null) {
				info.setCompany_name(proUser.getCompany_name());
				info.setService_category(proUser.getService_category());
				info.setService_description(proUser.getService_description());
				info.setCountrycode(proUser.getCountry_code());
				info.setPhone(proUser.getPhone_no());
				info.setEmail(proUser.getEmail());
				info.setAddress(proUser.getAddress());
			}
		}
 		return res;
	}

	public Register_res saveRegisterUserInfo_master(Register_req req, SysdateObjBean sysdate) throws Exception {
		Timestamp now = sysdate.getNow();
		long nowVersion = sysdate.getNowVersion();

		Register_req.Body body = req.getBody();
		String cellPhone = body.getCellphone();
		String countryCode = body.getCountrycode();
		String userName = body.getUsername();
		String pwd = Md5Util.getSign4String(body.getPwd(), "");
		String deviceId = body.getDeviceid();
		String retToken = CacheUtil.getToken(countryCode, cellPhone, nowVersion, deviceId);
		String realToken = CacheUtil.getRealToken(retToken, deviceId);

		TUserUsers uu = new TUserUsers();
		uu.setUser_type(Constant.USER_TYPE_CUSTOMER);
		uu.setPhone_no(cellPhone);
		uu.setCountry_code(countryCode);
		uu.setUser_name(userName);
		uu.setUser_pwd(pwd);
		uu.setState(Constant.STATE_IN_USE);
		uu.setState_date(now);
		uu.setCreate_date(now);
		uu.setCur_token(realToken);

		userDbDao.insertUser(uu, now);
		long userId = uu.getUser_id();

		cacheBaseUserInfoUpdate(uu, sysdate, realToken);
		niRegister(userId, userName, realToken, now);

		Register_res res = new Register_res();
		res.setToken(retToken);
		Register_res.User user = new Register_res.User();
		user.setId(userId);
		user.setLastupdate(now.getTime());
		res.setUser(user);
 		return res;
	}

	public void putRegisterCode(String countryCode, String cellPhone, String registerCode, int expireSec) {
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
			int expireSec) {
		String keystr = CacheUtil.getFindpasswordCacheKey(countryCode, cellPhone);
		userRedisDao.set(keystr, verificationCode, expireSec);
	}

	public void cancelUserInfoIntoRedis(String countryCode, String cellPhone) {
		String key = CacheUtil.getUserInfoCacheKey(countryCode, cellPhone);
		userRedisDao.delete(key);
	}

	public void niRegister(long userId, String userName, String token, Timestamp cur) throws Exception {
		Create_req register = new Create_req();
		register.setAccid(String.valueOf(userId));
		register.setName(userName);
		register.setToken(token);
		NiUtil.createAction(register, cur);
	}

	public void niTokenUpdate(long userId, String token, Timestamp cur) throws Exception {
		Update_req update = new Update_req();
		update.setAccid(String.valueOf(userId));
		update.setToken(token);

		NiUtil.updateTokenAction(update, cur);
	}

	public TokenMappingRds getTokenObj(String token) throws Exception {
 		try {
			String key = CacheUtil.getTokenCacheKey(token);
			return userRedisDao.getJsonObj(key);
		} catch (Exception e) {
			 List<TUserUsers>  list = userDbDao.queryUserByToken(token);
			 if(list.size() == 1){
				 TokenMappingRds tr = new TokenMappingRds();
				 tr.setUserId(list.get(0).getUser_id());
				 return tr;
			 }
		}
		return null;
	}

	public void deleteRedisToken(String token) {
		try {
			String key = CacheUtil.getTokenCacheKey(token);
			userRedisDao.delete(key);
		} catch (Exception e) {
			throw new RedisException("", e);
		}
	}
	//
	public TUserProUsers saveProsUserInfo_master(CreateSamPros_req req, TUserUsers users, SysdateObjBean sysdate) throws Exception {
		Timestamp now = sysdate.getNow();
		long nowVersion = sysdate.getNowVersion();
		
		CreateSamPros_req.Body body = req.getBody();
		long userId = users.getUser_id();
		TUserProUsers proUsers = new TUserProUsers();
		proUsers.setUser_id(userId);
		proUsers.setCompany_name(body.getCompany_name());
		proUsers.setService_category(body.getService_category());
		proUsers.setService_description(body.getService_description());
		proUsers.setCountry_code(body.getCountrycode());
		proUsers.setPhone_no(body.getPhone());
		proUsers.setEmail(body.getEmail());

		CreateSamPros_req.Location location = body.getLocation();
		if (location != null) {
			CreateSamPros_req.Location_info info = location.getLocation_info();
			if (info != null) {
				proUsers.setLongitude(info.getLongitude());
				proUsers.setLatitude(info.getLatitude());
			}
			proUsers.setPlace_id(location.getPlace_id());
			proUsers.setAddress(location.getAddress());
		}

		proUsers.setState(Constant.STATE_IN_USE);
		proUsers.setState_date(now);
		proUsers.setCreate_date(now);
		userDbDao.insertProUser(proUsers);

		TUserUsers userCon = new TUserUsers();
		userCon.setUser_id(userId);
		userCon.setUser_type(Constant.USER_TYPE_SERVICES);
		userCon.setState_date(now);
		userDbDao.updateUser(userCon);

		UserInfoProRds userProInfo = new UserInfoProRds();
		userProInfo.setNowVersion(nowVersion);
		PropertyUtils.copyProperties(userProInfo, proUsers);
		hsetUserInfoProsJsonObj(userId, userProInfo);
		
		UserInfoRds userInfo = new UserInfoRds();
		userInfo.setNowVersion(nowVersion);
		PropertyUtils.copyProperties(userInfo, users);
 		userInfo.setUser_type(userCon.getUser_type());
		userInfo.setState_date(userCon.getState_date());
		hsetUserInfoJsonObj(userId, userInfo);

		return proUsers;

	}

	public void updatePassword(long userId, String password, Timestamp sysdate) {
		TUserUsers userCon = new TUserUsers();
		userCon.setUser_id(userId);
		userCon.setUser_pwd(password);

		userCon.setState_date(sysdate);
		userDbDao.updateUser(userCon);
	}

	public TUserProUsers queryProUser(long userId) {
		return userDbDao.queryProUser(userId);
	}

	public void loginPwderrorCheck(String countryCode, String cellphone, Timestamp sysdate) throws Exception {
		LoginErrRds loginerr = this.userRedisDao.getJsonObj(CacheNameCacheEnum.RDS_LOGIN_ERR.val() + ":" + countryCode
				+ "_" + cellphone);
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
	
	public TUserUsers queryUser_master(long userId) {
		return queryUser(userId);
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

	public TUserUsers updateAvatar(String origin, String thumb, long userId, Timestamp sysdate) throws Exception {
		
		TUserUsers uu = queryUser(userId);
		uu.setUser_id(userId);
		uu.setAvatar_origin(origin);
		uu.setAvatar_thumb(S3Util.getThumbObject(origin));
		uu.setState_date(sysdate);
		userDbDao.updateUser(uu);

		UserInfoRds uur = new UserInfoRds();
		PropertyUtils.copyProperties(uur, uu);
		hsetUserInfoJsonObj(userId, uur);

		return uu;
	}
	
	public TUserUsers updateAvatar_master(String origin, String thumb, long userId, Timestamp sysdate)  throws Exception{
		return updateAvatar(origin, thumb, userId, sysdate);
	}

	public void updateDbToken(long userId, String token) {
		TUserUsers user = new TUserUsers();
		user.setUser_id(userId);
		user.setCur_token(token);
		userDbDao.updateUser(user);
	}
	
	public void deleteRedisUserInfo(long userId){
		String key = CacheUtil.getUserInfoIdCacheKey(userId);
		userRedisDao.delete(key);
	}
}
