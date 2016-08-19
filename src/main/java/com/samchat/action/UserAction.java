package com.samchat.action;

import java.sql.Timestamp;

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
import com.samchat.common.beans.auto.json.appserver.user.RegisterCodeRequest_req;
import com.samchat.common.beans.auto.json.appserver.user.RegisterCodeRequest_res;
import com.samchat.common.beans.auto.json.appserver.user.Register_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_res;
import com.samchat.common.beans.auto.json.appserver.user.SignupCodeVerify_req;
import com.samchat.common.beans.auto.json.appserver.user.SignupCodeVerify_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.Md5Util;
import com.samchat.common.utils.TwilioUtil;
import com.samchat.service.interfaces.IUsersSrv;

public class UserAction extends BaseAction {

	private static Logger log = Logger.getLogger(UserAction.class);

	@Autowired
	private IUsersSrv usersSrv;

	/**
	 * 注册验证码验证
	 * 
	 * @param req
	 * @return
	 */
	public SignupCodeVerify_res signupCodeVerify(SignupCodeVerify_req req) {
		SignupCodeVerify_res res = new SignupCodeVerify_res();
		res.setRet(Constant.SUCCESS);
		return res;
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
			throw new AppException(Constant.ERROR_PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone(cellphone, countryCode);
		if (userUsers != null) {
			throw new AppException(Constant.ERROR_PHONEorUSERNAME_EXIST);
		}
		String registerCode = usersSrv.getRegisterCode(countryCode, cellphone);
		if (registerCode == null) {
			throw new AppException(Constant.ERROR_REGISTER_CODE_EXPIRED);
		}
		if (!registerCode.equals(body.getVerifycode())) {
			throw new AppException(Constant.ERROR_VERIFICATION_CODE);
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

		RegisterCodeRequest_res res = new RegisterCodeRequest_res();
		res.setRet(Constant.SUCCESS);

		return res;
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
			throw new AppException(Constant.ERROR_PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone(cellphone, countryCode);
		if (userUsers != null) {
			throw new AppException(Constant.ERROR_PHONEorUSERNAME_EXIST);
		}
		if (usersSrv.getRegisterCode(countryCode, cellphone) != null) {
			throw new AppException(Constant.ERROR_VERIFICATION_CODE_FREQUENT);
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
			throw new AppException(Constant.ERROR_PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone(body.getCellphone(), body.getCountrycode());
		if (userUsers == null) {
			userUsers = usersSrv.queryUserInfoByUserName(body.getUsername());
		}
		if (userUsers != null) {
			throw new AppException(Constant.ERROR_PHONEorUSERNAME_EXIST);
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

		Login_res res = new Login_res();
		res.setRet(Constant.SUCCESS);

		Timestamp cur = usersSrv.querySysdate();
		String token = usersSrv.getAddedToken(cCode, cellPhone, cur.getTime(), deviceId);
		usersSrv.niTokenUpdate(userId, token, deviceId, cur);

		res.setToken(token);

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

		Sam_pros_info sam_pros_info = new Login_res.Sam_pros_info();
		userRes.setSam_pros_info(sam_pros_info);

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
			throw new AppException(Constant.ERROR_USER_NOT_EXIST);
		}
		if (!user.getUser_pwd().equals(pwd)) {
			throw new AppException(Constant.ERROR_USER_PWD);
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
	public Logout_res logout(Logout_req req) throws Exception {

		usersSrv.deleteToken(req.getHeader().getToken());

		Logout_res res = new Logout_res();
		res.setRet(Constant.SUCCESS);

		return res;
	}

	/**
	 * 退出校验
	 * 
	 * @param req
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void logoutValidate(Logout_req req) throws Exception {
		String token = req.getHeader().getToken();
		tokenIdentify(token);
	}

	/**
	 * 创建商户资料
	 * 
	 * @param req
	 * @return
	 */
	public CreateSamPros_res createSamPros(CreateSamPros_req req, TUserUsers user) {

		TUserProUsers proUsers = usersSrv.saveProsUserInfo(req, user);

		CreateSamPros_res res = new CreateSamPros_res();
		res.setRet(Constant.SUCCESS);
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

	public TUserUsers createSamProsValidate(CreateSamPros_req req) {
		String token = req.getHeader().getToken();
		TokenRds tokenObj = tokenIdentify(token);

		String countrycode = tokenObj.getCountryCode();
		String cellphone = tokenObj.getCellPhone();

		TUserUsers user = usersSrv.queryUserInfoByPhone(cellphone, countrycode);
		if (Constant.USER_TYPE_SERVICES == user.getUser_type()) {
			throw new AppException(Constant.ERROR_USER_PROS_EXIST);
		}
		return user;
	}

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

		String twilloPhoneNo = CommonUtil.getSysConfigStr(Constant.SYS_PARAM_KEY.TWILIO_PHONE_NO);
		TwilioUtil.sendSms(CommonUtil.getE164PhoneNo(countryCode, cellPhone), twilloPhoneNo, smsContent);

		FindpwdCodeRequest_res res = new FindpwdCodeRequest_res();
		res.setRet(Constant.SUCCESS);

		return res;
	}

	public void findpwdCodeRequestValidate(FindpwdCodeRequest_req req) {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(Constant.ERROR_PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user == null) {
			throw new AppException(Constant.ERROR_PHONE_NOT_EXIST);
		}
		String code = usersSrv.getFindpasswordVerificationCode(countryCode, cellPhone);
		if (code != null) {
			throw new AppException(Constant.ERROR_VERIFICATION_CODE_FREQUENT);
		}
	}

	public FindpwdCodeVerify_res findpwdCodeVerify(FindpwdCodeVerify_req req) {
		FindpwdCodeVerify_res res = new FindpwdCodeVerify_res();
		res.setRet(Constant.SUCCESS);
		return res;
	}

	public void findpwdCodeVerifyValidate(FindpwdCodeVerify_req req) {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(Constant.ERROR_PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user == null) {
			throw new AppException(Constant.ERROR_PHONE_NOT_EXIST);
		}
		String code = usersSrv.getFindpasswordVerificationCode(countryCode, cellPhone);
		if (!req.getBody().getVerifycode().equals(code)) {
			throw new AppException(Constant.ERROR_VERIFICATION_CODE);
		}
	}

	public FindpwdUpdate_res findpwdUpdate(FindpwdUpdate_req req, TUserUsers user) throws Exception {
		String password = Md5Util.getSign4String(req.getBody().getPwd(), "");
		usersSrv.updatePassword(user.getUser_id(), password);

		FindpwdUpdate_res res = new FindpwdUpdate_res();
		res.setRet(Constant.SUCCESS);
		return res;

	}

	public TUserUsers findpwdUpdateValidate(FindpwdUpdate_req req) {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(Constant.ERROR_PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user == null) {
			throw new AppException(Constant.ERROR_PHONE_NOT_EXIST);
		}
		String code = usersSrv.getFindpasswordVerificationCode(countryCode, cellPhone);
		if (!req.getBody().getVerifycode().equals(code)) {
			throw new AppException(Constant.ERROR_VERIFICATION_CODE);
		}
		return user;
	}

	public PwdUpdate_res pwdUpdate(PwdUpdate_req req, TUserUsers user) throws Exception {
		String newPwd = Md5Util.getSign4String(req.getBody().getNew_pwd(), "");
		usersSrv.updatePassword(user.getUser_id(), newPwd);

		PwdUpdate_res res = new PwdUpdate_res();
		res.setRet(Constant.SUCCESS);

		return res;
	}

	public TUserUsers pwdUpdateValidate(PwdUpdate_req req) {

		String token = req.getHeader().getToken();
		TokenRds tokenObj = tokenIdentify(token);

		String countrycode = tokenObj.getCountryCode();
		String cellphone = tokenObj.getCellPhone();

		TUserUsers user = usersSrv.queryUserInfoByPhone(cellphone, countrycode);
		if (req.getBody().getOld_pwd().equals(user.getUser_pwd())) {
			throw new AppException(Constant.ERROR_USER_OLD_PWD);
		}

		return user;
	}
}
