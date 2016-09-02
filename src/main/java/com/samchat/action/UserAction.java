package com.samchat.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.user.CreateSamPros_req;
import com.samchat.common.beans.auto.json.appserver.user.CreateSamPros_res;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdCodeRequest_req;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdCodeRequest_res;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdCodeVerify_req;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdCodeVerify_res;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdUpdate_req;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdUpdate_res;
import com.samchat.common.beans.auto.json.appserver.user.Login_req;
import com.samchat.common.beans.auto.json.appserver.user.Login_res;
import com.samchat.common.beans.auto.json.appserver.user.Login_res.Sam_pros_info;
import com.samchat.common.beans.auto.json.appserver.user.Logout_req;
import com.samchat.common.beans.auto.json.appserver.user.Logout_res;
import com.samchat.common.beans.auto.json.appserver.user.PwdUpdate_req;
import com.samchat.common.beans.auto.json.appserver.user.PwdUpdate_res;
import com.samchat.common.beans.auto.json.appserver.user.QueryAccurate_req;
import com.samchat.common.beans.auto.json.appserver.user.QueryAccurate_res;
import com.samchat.common.beans.auto.json.appserver.user.QueryFuzzy_req;
import com.samchat.common.beans.auto.json.appserver.user.QueryFuzzy_res;
import com.samchat.common.beans.auto.json.appserver.user.QueryGroup_req;
import com.samchat.common.beans.auto.json.appserver.user.QueryGroup_res;
import com.samchat.common.beans.auto.json.appserver.user.QueryWithoutToken_req;
import com.samchat.common.beans.auto.json.appserver.user.QueryWithoutToken_res;
import com.samchat.common.beans.auto.json.appserver.user.RegisterCodeRequest_req;
import com.samchat.common.beans.auto.json.appserver.user.RegisterCodeRequest_res;
import com.samchat.common.beans.auto.json.appserver.user.Register_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_res;
import com.samchat.common.beans.auto.json.appserver.user.SignupCodeVerify_req;
import com.samchat.common.beans.auto.json.appserver.user.SignupCodeVerify_res;
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.Md5Util;
import com.samchat.common.utils.TwilioUtil;
import com.samchat.service.interfaces.ICommonSrv;
import com.samchat.service.interfaces.IUsersSrv;

public class UserAction extends BaseAction {

	private static Logger log = Logger.getLogger(UserAction.class);

	@Autowired
	private IUsersSrv usersSrv;

	@Autowired
	private ICommonSrv commonSrv;

	/**
	 * 注册验证码验证
	 * 
	 * @param req
	 * @return
	 */
	public SignupCodeVerify_res signupCodeVerify(SignupCodeVerify_req req) {
		return new SignupCodeVerify_res();
	}

	/**
	 * 注册验证码验证 信息校验
	 * 
	 * @param req
	 * @return
	 */
	public void signupCodeVerifyValidate(SignupCodeVerify_req req) {
		SignupCodeVerify_req.Body body = req.getBody();

		String countryCode = body.getCountrycode();
		String cellphone = body.getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellphone)) {
			throw new AppException(Constant.ERROR.PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone(cellphone, countryCode);
		if (userUsers != null) {
			throw new AppException(Constant.ERROR.PHONEorUSERNAME_EXIST);
		}
		String registerCode = usersSrv.getRegisterCode(countryCode, cellphone);
		if (registerCode == null) {
			throw new AppException(Constant.ERROR.REGISTER_CODE_EXPIRED);
		}
		if (!registerCode.equals(body.getVerifycode())) {
			throw new AppException(Constant.ERROR.VERIFICATION_CODE);
		}
	}

	/**
	 * 注册验证码申请
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public RegisterCodeRequest_res registerCodeRequest(RegisterCodeRequest_req req) throws Exception {

		RegisterCodeRequest_req.Body body = req.getBody();
		String countrycode = body.getCountrycode();
		String cellphone = body.getCellphone();
		String registerCode = CommonUtil.getRadom(4);
		log.info("countryCode:" + countrycode + "--" + "cellphone:" + cellphone + "--registerCode:" + registerCode);

		int timeToIdle = CommonUtil.getSysConfigInt(Constant.SYS_PARAM_KEY.REGISTER_CODE_TIME_TO_IDLE);

		registerCode = "1234";// dev_m
		usersSrv.putRegisterCode(countrycode, cellphone, registerCode, timeToIdle);
		String smstpl = CommonUtil
				.getSysConfigStr(Constant.SYS_PARAM_KEY.TWILIO_VERIFICATION_REGISTER_CODE_SMS_TEMPLETE);
		String smsContent = smstpl.replaceAll(Constant.TWILLO_VERIFICATION_CODE, registerCode);

		String twilloPhoneNo = CommonUtil.getSysConfigStr(Constant.SYS_PARAM_KEY.TWILIO_PHONE_NO);
		TwilioUtil.sendSms(CommonUtil.getE164PhoneNo(countrycode, cellphone), twilloPhoneNo, smsContent);
		return new RegisterCodeRequest_res();
	}

	/**
	 * 注册验证码申请 信息校验
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public void registerCodeRequestValidate(RegisterCodeRequest_req req) throws Exception {
		RegisterCodeRequest_req.Body body = req.getBody();

		String countryCode = body.getCountrycode();
		String cellphone = body.getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(body.getCellphone())) {
			throw new AppException(Constant.ERROR.PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone(cellphone, countryCode);
		if (userUsers != null) {
			throw new AppException(Constant.ERROR.PHONEorUSERNAME_EXIST);
		}
		if (usersSrv.getRegisterCode(countryCode, cellphone) != null) {
			throw new AppException(Constant.ERROR.VERIFICATION_CODE_FREQUENT);
		}
	}

	/**
	 * 注册请求
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public Register_res register(Register_req req) throws Exception {

		return usersSrv.saveRegisterUserInfo(req);

	}

	/**
	 * 注册请求 信息校验
	 * 
	 * @param req
	 */
	public void registerValidate(Register_req req) {

		Register_req.Body body = req.getBody();

		if (!CommonUtil.phoneNoFormatValidate(body.getCellphone())) {
			throw new AppException(Constant.ERROR.PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone(body.getCellphone(), body.getCountrycode());
		if (userUsers == null) {
			userUsers = usersSrv.queryUserInfoByUserName(body.getUsername());
		}
		if (userUsers != null) {
			throw new AppException(Constant.ERROR.PHONEorUSERNAME_EXIST);
		}
	}

	/**
	 * 登录
	 * 
	 * @param req
	 * @return
	 */
	public Login_res login(Login_req req, TUserUsers user) throws Exception {

		Login_req.Body body = req.getBody();
		String deviceId = body.getDeviceid();

		long userId = user.getUser_id();
		String cellPhone = user.getPhone_no();
		String cCode = user.getCountry_code();
		UserInfoRds userfo = usersSrv.getUserInfoIntoRedis(cCode, cellPhone);
   		if (userfo != null) {
			usersSrv.deleteToken(userfo.getToken());
		}

		Login_res res = new Login_res();

		Timestamp cur = commonSrv.querySysdate();
		String[] token = usersSrv.getAddedToken(cCode, cellPhone, cur.getTime(), deviceId, userId);

		String retToken = token[0];
		String realToken = token[1];

		usersSrv.setUserInfoIntoRedis(cCode, cellPhone, realToken);
		usersSrv.niTokenUpdate(userId, realToken, cur);
		res.setToken(retToken);

		Login_res.User userRes = new Login_res.User();

		userRes.setId(userId);
		userRes.setUsername(user.getUser_name());
		userRes.setType(user.getUser_type());
		userRes.setCountrycode(cCode);
		userRes.setCellphone(user.getPhone_no());
		userRes.setAddress("");
		userRes.setEmail(user.getEmail());
		userRes.setLastupdate(user.getState_date().getTime());

		Login_res.Avatar avatar = new Login_res.Avatar();
		avatar.setOrigin(user.getAvatar_origin());
		avatar.setThumb(user.getAvatar_thumb());
		userRes.setAvatar(avatar);

		Sam_pros_info info = new Login_res.Sam_pros_info();
		userRes.setSam_pros_info(info);
		if (user.getUser_type() == Constant.USER_TYPE_SERVICES) {
			TUserProUsers proUser = usersSrv.queryProUser(userId);
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
		res.setUser(userRes);
		return res;
	}

	/**
	 * 登录校验
	 * 
	 * @param req
	 * @throws Exception
	 */
	public TUserUsers loginValidate(Login_req req) throws Exception {
		Login_req.Body body = req.getBody();
		String account = body.getAccount();
		String countryCode = body.getCountrycode();
		String pwd = Md5Util.getSign4String(body.getPwd(), "");
		String deviceId = body.getDeviceid();

		TUserUsers user = usersSrv.queryUserInfoByPhone(account, countryCode);
		if (user == null) {
			user = usersSrv.queryUserInfoByUserName(account);
		}
		if (user == null) {
			throw new AppException(Constant.ERROR.USER_NOT_EXIST);
		}
		if (!user.getUser_pwd().equals(pwd)) {
			throw new AppException(Constant.ERROR.USER_PWD);
		}
		return user;
	}

	/**
	 * 退出
	 * 
	 * @param req
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Logout_res logout(Logout_req req, TokenRds token) throws Exception {

		usersSrv.deleteToken(req.getHeader().getToken());

		return new Logout_res();
	}

	/**
	 * 退出校验
	 * 
	 * @param req
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void logoutValidate(Logout_req req, TokenRds token) throws Exception {
	}

	/**
	 * 创建商户资料
	 * 
	 * @param req
	 * @return
	 */
	public CreateSamPros_res createSamPros(CreateSamPros_req req, TokenRds token, TUserUsers user) {

		TUserProUsers proUsers = usersSrv.saveProsUserInfo(req, user);

		CreateSamPros_res res = new CreateSamPros_res();

		CreateSamPros_res.User userRet = new CreateSamPros_res.User();
		userRet.setId(user.getUser_id());
		userRet.setUsername(user.getUser_name());
		userRet.setCountrycode(user.getCountry_code());
		userRet.setCellphone(user.getPhone_no());
		userRet.setEmail(user.getEmail());
		userRet.setAddress(user.getAddress());
		userRet.setType(Constant.USER_TYPE_SERVICES);

		CreateSamPros_res.Avatar avatar = new CreateSamPros_res.Avatar();
		avatar.setOrigin(user.getAvatar_origin());
		avatar.setThumb(user.getAvatar_thumb());
		userRet.setAvatar(avatar);
		userRet.setLastupdate(proUsers.getState_date().getTime());
		userRet.setAvatar(avatar);

		res.setUser(userRet);

		return res;
	}

	public TUserUsers createSamProsValidate(CreateSamPros_req req, TokenRds token) {

		TUserUsers user = usersSrv.queryUser(token.getUserId());
		if (Constant.USER_TYPE_SERVICES == user.getUser_type()) {
			throw new AppException(Constant.ERROR.USER_PROS_EXIST);
		}
		return user;
	}

	/**
	 * 密码找回验证码申请
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public FindpwdCodeRequest_res findpwdCodeRequest(FindpwdCodeRequest_req req) throws Exception {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();
		String verificationCode = CommonUtil.getRadom(4);
		verificationCode = "1234";
		int timeToIdle = CommonUtil.getSysConfigInt(Constant.SYS_PARAM_KEY.FIND_PASSWORD_CODE_TIME_TO_IDLE);
		usersSrv.putFindpasswordVerificationCode(countryCode, cellPhone, verificationCode, timeToIdle);

		String smstpl = CommonUtil
				.getSysConfigStr(Constant.SYS_PARAM_KEY.TWILIO_VERIFICATION_FINDPWD_CODE_SMS_TEMPLETE);
		String smsContent = smstpl.replaceAll(Constant.TWILLO_VERIFICATION_CODE, verificationCode);

		TwilioUtil.sendSms(countryCode, cellPhone, smsContent);

		return new FindpwdCodeRequest_res();
	}

	public void findpwdCodeRequestValidate(FindpwdCodeRequest_req req) {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(Constant.ERROR.PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user == null) {
			throw new AppException(Constant.ERROR.PHONE_NOT_EXIST);
		}
		String code = usersSrv.getFindpasswordVerificationCode(countryCode, cellPhone);
		if (code != null) {
			throw new AppException(Constant.ERROR.VERIFICATION_CODE_FREQUENT);
		}
	}

	/**
	 * 密码找回时，验证码验证
	 * 
	 * @param req
	 * @return
	 */
	public FindpwdCodeVerify_res findpwdCodeVerify(FindpwdCodeVerify_req req) {
		return new FindpwdCodeVerify_res();
	}

	public void findpwdCodeVerifyValidate(FindpwdCodeVerify_req req) {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(Constant.ERROR.PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user == null) {
			throw new AppException(Constant.ERROR.PHONE_NOT_EXIST);
		}
		String code = usersSrv.getFindpasswordVerificationCode(countryCode, cellPhone);
		if (!req.getBody().getVerifycode().equals(code)) {
			throw new AppException(Constant.ERROR.VERIFICATION_CODE);
		}
	}

	/**
	 * 密码找回时 密码更新
	 * 
	 * @param req
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public FindpwdUpdate_res findpwdUpdate(FindpwdUpdate_req req, TUserUsers user) throws Exception {
		String password = Md5Util.getSign4String(req.getBody().getPwd(), "");
		usersSrv.updatePassword(user.getUser_id(), password);
		return new FindpwdUpdate_res();

	}

	public TUserUsers findpwdUpdateValidate(FindpwdUpdate_req req) {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(Constant.ERROR.PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user == null) {
			throw new AppException(Constant.ERROR.PHONE_NOT_EXIST);
		}
		String code = usersSrv.getFindpasswordVerificationCode(countryCode, cellPhone);
		if (!req.getBody().getVerifycode().equals(code)) {
			throw new AppException(Constant.ERROR.VERIFICATION_CODE);
		}
		return user;
	}

	/**
	 * 登录状态下密码更新
	 * 
	 * @param req
	 * @param token
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public PwdUpdate_res pwdUpdate(PwdUpdate_req req, TokenRds token, TUserUsers user) throws Exception {
		String newPwd = Md5Util.getSign4String(req.getBody().getNew_pwd(), "");
		usersSrv.updatePassword(user.getUser_id(), newPwd);
		return new PwdUpdate_res();
	}

	public TUserUsers pwdUpdateValidate(PwdUpdate_req req, TokenRds token) {

		TUserUsers user = usersSrv.queryUser(token.getUserId());
		if (req.getBody().getOld_pwd().equals(user.getUser_pwd())) {
			throw new AppException(Constant.ERROR.USER_OLD_PWD);
		}
		return user;
	}

	public QueryFuzzy_res queryFuzzy(QueryFuzzy_req req, TokenRds token) {
		QueryFuzzy_req.Param p = req.getBody().getParam();
		String key = p.getSearch_key();
		List<QryUserInfoVO> userlist = usersSrv.queryUsersFuzzy(key);

		ArrayList<QueryFuzzy_res.Users> users = new ArrayList<QueryFuzzy_res.Users>();

		for (QryUserInfoVO pq : userlist) {

			QueryFuzzy_res.Users user = new QueryFuzzy_res.Users();
			user.setId(pq.getUser_id());
			user.setUsername(pq.getUser_name());
			user.setCountrycode(pq.getCountrycode());
			user.setCellphone(pq.getCellphone());
			user.setEmail(pq.getEmail());
			user.setAddress(pq.getAddress());
			user.setType(pq.getType());

			QueryFuzzy_res.Avatar avatar = new QueryFuzzy_res.Avatar();
			user.setAvatar(avatar);
			avatar.setOrigin(pq.getOrigin());
			avatar.setThumb(pq.getThumb());

			user.setLastupdate(pq.getLastupdate().getTime());

			QueryFuzzy_res.Sam_pros_info pros = new QueryFuzzy_res.Sam_pros_info();
			user.setSam_pros_info(pros);
			pros.setCompany_name(pq.getCompany_name());
			pros.setService_category(pq.getService_category());
			pros.setService_description(pq.getService_description());
			pros.setCountrycode(pq.getCountrycode_pro());
			pros.setPhone(pq.getPhone_pro());
			pros.setEmail(pq.getEmail_pro());
			pros.setAddress(pq.getAddress_pro());

			users.add(user);

		}

		QueryFuzzy_res res = new QueryFuzzy_res();
		res.setCount(users.size());
		res.setUsers(users);

		return res;
	}

	public void queryFuzzyValidate(QueryFuzzy_req req, TokenRds token) {
	}

	public QueryAccurate_res queryAccurate(QueryAccurate_req req, TokenRds token) {

		QueryAccurate_req.Param p = req.getBody().getParam();
		String cellphone = p.getCellphone().replaceAll("\\+| ", "");
		String username = p.getUsername();
		String id = p.getUnique_id();
		Long type = p.getType();
		List<QryUserInfoVO> userlist = usersSrv.queryUserAccurate(type, cellphone, username, id);
		ArrayList<QueryAccurate_res.Users> users = new ArrayList<QueryAccurate_res.Users>();

		for (QryUserInfoVO pq : userlist) {

			QueryAccurate_res.Users user = new QueryAccurate_res.Users();
			user.setId(pq.getUser_id());
			user.setUsername(pq.getUser_name());
			user.setCountrycode(pq.getCountrycode());
			user.setCellphone(pq.getCellphone());
			user.setEmail(pq.getEmail());
			user.setAddress(pq.getAddress());
			user.setType(pq.getType());

			QueryAccurate_res.Avatar avatar = new QueryAccurate_res.Avatar();
			user.setAvatar(avatar);
			avatar.setOrigin(pq.getOrigin());
			avatar.setThumb(pq.getThumb());

			user.setLastupdate(pq.getLastupdate().getTime());

			QueryAccurate_res.Sam_pros_info pros = new QueryAccurate_res.Sam_pros_info();
			user.setSam_pros_info(pros);
			pros.setCompany_name(pq.getCompany_name());
			pros.setService_category(pq.getService_category());
			pros.setService_description(pq.getService_description());
			pros.setCountrycode(pq.getCountrycode_pro());
			pros.setPhone(pq.getPhone_pro());
			pros.setEmail(pq.getEmail_pro());
			pros.setAddress(pq.getAddress_pro());

			users.add(user);

		}

		QueryAccurate_res res = new QueryAccurate_res();
		res.setCount(users.size());
		res.setUsers(users);

		return res;
	}

	public void queryAccurateValidate(QueryAccurate_req req, TokenRds token) {

	}

	public QueryGroup_res queryGroup(QueryGroup_req req, TokenRds token) {
		QueryGroup_req.Param param = req.getBody().getParam();
		ArrayList<Long> userIds = param.getUnique_id();
		List<QryUserInfoVO> userlist = usersSrv.queryUsersGroup(userIds);

		ArrayList<QueryGroup_res.Users> users = new ArrayList<QueryGroup_res.Users>();

		for (QryUserInfoVO pq : userlist) {

			QueryGroup_res.Users user = new QueryGroup_res.Users();
			user.setId(pq.getUser_id());
			user.setUsername(pq.getUser_name());
			user.setCountrycode(pq.getCountrycode());
			user.setCellphone(pq.getCellphone());
			user.setEmail(pq.getEmail());
			user.setAddress(pq.getAddress());
			user.setType(pq.getType());

			QueryGroup_res.Avatar avatar = new QueryGroup_res.Avatar();
			user.setAvatar(avatar);
			avatar.setOrigin(pq.getOrigin());
			avatar.setThumb(pq.getThumb());

			user.setLastupdate(pq.getLastupdate().getTime());

			QueryGroup_res.Sam_pros_info pros = new QueryGroup_res.Sam_pros_info();
			user.setSam_pros_info(pros);
			pros.setCompany_name(pq.getCompany_name());
			pros.setService_category(pq.getService_category());
			pros.setService_description(pq.getService_description());
			pros.setCountrycode(pq.getCountrycode_pro());
			pros.setPhone(pq.getPhone_pro());
			pros.setEmail(pq.getEmail_pro());
			pros.setAddress(pq.getAddress_pro());

			users.add(user);
		}

		QueryGroup_res res = new QueryGroup_res();
		res.setCount(users.size());
		res.setUsers(users);

		return res;
	}

	public void queryGroupValidate(QueryGroup_req req, TokenRds token) {
 	}

	public QueryWithoutToken_res queryWithoutToken(QueryWithoutToken_req req) {
		QueryWithoutToken_req.Param param = req.getBody().getParam();
		String cellphone = param.getCellphone();
		String countrycode = param.getCountrycode();
		String userName = param.getUsername();
		long type = param.getType();
		
		List<TUserUsers> users = usersSrv.queryUserWithoutToken(type, countrycode, cellphone, userName);
		
		QueryWithoutToken_res res = new QueryWithoutToken_res();
		res.setCount(users.size());
		
		return res;
	}

	public void queryWithoutTokenValidate(QueryWithoutToken_req req) {
	}

	
}
