package com.samchat.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.user.CreateSamPros_req;
import com.samchat.common.beans.auto.json.appserver.user.CreateSamPros_res;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdCodeRequest_req;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdCodeRequest_res;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdCodeVerify_req;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdCodeVerify_res;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdUpdate_req;
import com.samchat.common.beans.auto.json.appserver.user.FindpwdUpdate_res;
import com.samchat.common.beans.auto.json.appserver.user.LoginCodeRequest_req;
import com.samchat.common.beans.auto.json.appserver.user.LoginCodeRequest_res;
import com.samchat.common.beans.auto.json.appserver.user.Login_req;
import com.samchat.common.beans.auto.json.appserver.user.Login_res;
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
import com.samchat.common.beans.manual.common.SysdateObjBean;
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.redis.UserInfoProRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.enums.app.UserAppEnum;
import com.samchat.common.enums.db.SysMsgTplDbEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.TwilioUtil;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class UserAction extends BaseAction {

	private static Logger log = Logger.getLogger(UserAction.class);

	@Autowired
	private IUsersSrvs usersSrv;

	@Autowired
	private ICommonSrvs commonSrv;


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
		
		String code = usersSrv.getRegisterCode(countrycode, cellphone);
		String registerCode = code;
		if(registerCode == null){
			registerCode = CommonUtil.getRadom(4);
		}
		log.info("countryCode:" + countrycode + "--" + "cellphone:" + cellphone + "--registerCode:" + registerCode);

		String smstpl = CommonUtil.getSysMsgTpl(SysMsgTplDbEnum.ActionCode.REGISTER_CODE_SMS.val());
		String smsContent = smstpl.replaceAll(Constant.TWILLO_VERIFICATION_CODE, registerCode);
		log.info("smsContent:" + smsContent);

		String twilloPhoneNo = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.TWILIO_PHONE_NO.getParamCode());
		TwilioUtil.sendSms(CommonUtil.getE164PhoneNo(countrycode, cellphone), twilloPhoneNo, smsContent);
		
		if(code == null){
			usersSrv.putRegisterCode(countrycode, cellphone, registerCode);
		}
		usersSrv.putRegisterCodeCtrl(countrycode, cellphone);
		
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
			throw new AppException(ResCodeAppEnum.PHONE_FORMAT_ILLEGAL.getCode());
		}
		if (usersSrv.getRegisterCodeCtrl(countryCode, cellphone) != null) {
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE_FREQUENT.getCode());
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone_master(cellphone, countryCode);
		if (userUsers != null) {
			throw new AppException(ResCodeAppEnum.PHONEorUSERNAME_EXIST.getCode());
		}

	}
	
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
			throw new AppException(ResCodeAppEnum.PHONE_FORMAT_ILLEGAL.getCode());
		}
		String code = usersSrv.getRegisterCode(countryCode, cellphone);
		if (code == null) {
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE_EXPIRED.getCode());
		} else if (!code.equals(body.getVerifycode())) {
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE.getCode());
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone_master(cellphone, countryCode);
		if (userUsers != null) {
			throw new AppException(ResCodeAppEnum.PHONEorUSERNAME_EXIST.getCode());
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
		SysdateObjBean sysdate = commonSrv.querySysdateObj();
		Register_res res = usersSrv.saveRegisterUserInfo_master(req, sysdate);

		List<TSysConfigs> scslist = commonSrv.queryAllSysconfigsForApp();
		ArrayList<Register_res.Sys_params> paramslist = new ArrayList<Register_res.Sys_params>();

		for (TSysConfigs scs : scslist) {
			Register_res.Sys_params param = new Register_res.Sys_params();
			param.setParam_code(scs.getParam_code());
			param.setParam_value(scs.getParam_value());
			paramslist.add(param);
		}
		res.setSys_params(paramslist);

		return res;

	}

	/**
	 * 注册请求 信息校验
	 * 
	 * @param req
	 */
	public void registerValidate(Register_req req) {

		Register_req.Body body = req.getBody();
		
		if(!CommonUtil.appVersionCheck(body.getDevice_type(), body.getApp_version())){
			throw new AppException(ResCodeAppEnum.VERSION_NOSUPPORT.getCode());
		}
		if (!CommonUtil.phoneNoFormatValidate(body.getCellphone())) {
			throw new AppException(ResCodeAppEnum.PHONE_FORMAT_ILLEGAL.getCode());
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone_master(body.getCellphone(), body.getCountrycode());
		if (userUsers != null) {
			throw new AppException(ResCodeAppEnum.PHONEorUSERNAME_EXIST.getCode());
		}
	}
	
	/**
	 * 登录验证码申请
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public LoginCodeRequest_res loginCodeRequest(LoginCodeRequest_req req) throws Exception {

		LoginCodeRequest_req.Body body = req.getBody();
		String countrycode = body.getCountrycode();
		String cellphone = body.getCellphone();
		
		String code = usersSrv.getLoginCode(countrycode, cellphone);
		String loginCode = code;
		if(loginCode == null){
			loginCode = CommonUtil.getRadom(4);
		}
		log.info("countryCode:" + countrycode + "--" + "cellphone:" + cellphone + "--loginCode:" + loginCode);

		String smstpl = CommonUtil.getSysMsgTpl(SysMsgTplDbEnum.ActionCode.LOGIN_CODE_SMS.val());
		String smsContent = smstpl.replaceAll(Constant.TWILLO_VERIFICATION_CODE, loginCode);
		log.info("smsContent:" + smsContent);

		String twilloPhoneNo = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.TWILIO_PHONE_NO.getParamCode());
		TwilioUtil.sendSms(CommonUtil.getE164PhoneNo(countrycode, cellphone), twilloPhoneNo, smsContent);
		
		if(code == null){
			usersSrv.putLoginCode(countrycode, cellphone, loginCode);
		}
		usersSrv.putLoginCodeCtrl(countrycode, cellphone);
		
		return new LoginCodeRequest_res();
	}

	/**
	 * 注册验证码申请 信息校验
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public void loginCodeRequestValidate(LoginCodeRequest_req req) throws Exception {
		LoginCodeRequest_req.Body body = req.getBody();

		String countryCode = body.getCountrycode();
		String cellphone = body.getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(body.getCellphone())) {
			throw new AppException(ResCodeAppEnum.PHONE_FORMAT_ILLEGAL.getCode());
		}
		if (usersSrv.getLoginCodeCtrl(countryCode, cellphone) != null) {
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE_FREQUENT.getCode());
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone_master(cellphone, countryCode);
		if (userUsers == null) {
			throw new AppException(ResCodeAppEnum.USER_NOT_EXIST.getCode());
		}

	}

	/**
	 * 登录
	 * 
	 * @param req
	 * @return
	 */
	public Login_res login(Login_req req, TUserUsers user) throws Exception {
		SysdateObjBean sysdate = commonSrv.querySysdateObj();
		Login_res res = usersSrv.saveLoginUserInfo_master(req, user, sysdate);
 		List<TSysConfigs> scslist = commonSrv.queryAllSysconfigsForApp();
		ArrayList<Login_res.Sys_params> paramslist = new ArrayList<Login_res.Sys_params>();

		for (TSysConfigs scs : scslist) {
			Login_res.Sys_params param = new Login_res.Sys_params();
			param.setParam_code(scs.getParam_code());
			param.setParam_value(scs.getParam_value());
			paramslist.add(param);
		}
		res.setSys_params(paramslist);

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
		long type = body.getType();
		String account = body.getAccount();
		String countryCode = body.getCountrycode();
		String pwd = body.getPwd();//Md5Util.getSign4String(body.getPwd(), "");
		String verifycode = body.getVerifycode();
		String appVersion = body.getApp_version();
		String deviceType = body.getDevice_type();
		
		if(!CommonUtil.appVersionCheck(deviceType, appVersion)){
			throw new AppException(ResCodeAppEnum.VERSION_NOSUPPORT.getCode());
		}
		TUserUsers user = null;
		if(UserAppEnum.LoginType.SMS_VERIFYCODE.val() == type){
			String code = usersSrv.getLoginCode(countryCode, account);
			if(code == null){
				throw new AppException(ResCodeAppEnum.VERIFICATION_CODE_EXPIRED.getCode());
			}
			if(!code.equals(verifycode)){
				throw new AppException(ResCodeAppEnum.VERIFICATION_CODE.getCode());
			}
			user = usersSrv.queryUserInfoByPhone_master(account, countryCode);
		}else{
			if(UserAppEnum.LoginType.SAMCHAT_ID.val() == type){
				user = usersSrv.queryUserInfoByUsercode_master(account);
			}else if(UserAppEnum.LoginType.CELLPHONE.val() == type){
				user = usersSrv.queryUserInfoByPhone_master(account, countryCode);
			}
			if (user == null) {
				throw new AppException(ResCodeAppEnum.USER_NOT_EXIST.getCode());
			}
			if (pwd == null || !pwd.equals(user.getUser_pwd())) {
				throw new AppException(ResCodeAppEnum.USER_PWD.getCode());
			}
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
	public Logout_res logout(Logout_req req, TokenMappingRds token) throws Exception {

		usersSrv.deleteRedisToken(req.getHeader().getToken());
		
		TUserUsers user = usersSrv.queryUser(token.getUserId());
		String userPwd = user.getUser_pwd();
		Logout_res res = new Logout_res();
		if(userPwd == null || "".equals(userPwd)){
			res.setPwd_flag(new Long(UserAppEnum.PwdFlag.PWD_NULL.val()));
		}else{
			res.setPwd_flag(new Long(UserAppEnum.PwdFlag.PWD_NOT_NULL.val()));
		}
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
	public void logoutValidate(Logout_req req, TokenMappingRds token) throws Exception {
	}

	/**
	 * 创建商户资料
	 * 
	 * @param req
	 * @return
	 */
	public CreateSamPros_res createSamPros(CreateSamPros_req req, TokenMappingRds token, TUserUsers user) throws Exception {

		SysdateObjBean sysdate = commonSrv.querySysdateObj();
		usersSrv.saveProsUserInfo_master(req, user, sysdate);

		CreateSamPros_res res = new CreateSamPros_res();
		CreateSamPros_res.User userRet = new CreateSamPros_res.User();
		userRet.setLastupdate(sysdate.getNow().getTime());
 		res.setUser(userRet);

		return res;
	}

	public TUserUsers createSamProsValidate(CreateSamPros_req req, TokenMappingRds token) {

		TUserUsers user = usersSrv.queryUser_master(token.getUserId());
		if (Constant.USER_TYPE_SERVICES == user.getUser_type()) {
			throw new AppException(ResCodeAppEnum.USER_PROS_EXIST.getCode());
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
		
		String code = usersSrv.getFindpasswordVerificationCode(countryCode, cellPhone);
		String verificationCode = null;
		if(verificationCode == null){
			verificationCode = CommonUtil.getRadom(4);
		}
		log.info("countryCode:" + countryCode + "--" + "cellphone:" + cellPhone + "--verificationCode:" + verificationCode);
		
 		String smstpl = CommonUtil.getSysMsgTpl(SysMsgTplDbEnum.ActionCode.FINDPWD_CODE_SMS.val());
		String smsContent = smstpl.replaceAll(Constant.TWILLO_VERIFICATION_CODE, verificationCode);
		log.info("smsContent:" + smsContent);
		TwilioUtil.sendSms(countryCode, cellPhone, smsContent);
		
		if(code == null){
			usersSrv.putFindpasswordVerificationCode(countryCode, cellPhone, verificationCode);
		}
		usersSrv.putFindpasswordVerificationCodeCtrl(countryCode, cellPhone);

		return new FindpwdCodeRequest_res();
	}

	public void findpwdCodeRequestValidate(FindpwdCodeRequest_req req) {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(ResCodeAppEnum.PHONE_FORMAT_ILLEGAL.getCode());
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user == null) {
			throw new AppException(ResCodeAppEnum.PHONE_NOT_EXIST.getCode());
		}
		String code = usersSrv.getFindpasswordVerificationCodeCtrl(countryCode, cellPhone);
		if (code != null) {
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE_FREQUENT.getCode());
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
		
		FindpwdCodeVerify_req.Body body = req.getBody();
		String countryCode = body.getCountrycode();
		String cellPhone = body.getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(ResCodeAppEnum.PHONE_FORMAT_ILLEGAL.getCode());
		}
		String code = usersSrv.getFindpasswordVerificationCode(countryCode, cellPhone);
		if(code == null){
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE_EXPIRED.getCode());
		} else if (!code.equals(body.getVerifycode())) {
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE.getCode());
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user == null) {
			throw new AppException(ResCodeAppEnum.PHONE_NOT_EXIST.getCode());
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
		String password = req.getBody().getPwd();//Md5Util.getSign4String(req.getBody().getPwd(), "");
		Timestamp sysdate = commonSrv.querySysdate();
		usersSrv.updatePassword(user.getUser_id(), password, sysdate);
		return new FindpwdUpdate_res();

	}

	public TUserUsers findpwdUpdateValidate(FindpwdUpdate_req req) {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(ResCodeAppEnum.PHONE_FORMAT_ILLEGAL.getCode());
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user == null) {
			throw new AppException(ResCodeAppEnum.PHONE_NOT_EXIST.getCode());
		}
		String code = usersSrv.getFindpasswordVerificationCode(countryCode, cellPhone);
		if (!req.getBody().getVerifycode().equals(code)) {
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE.getCode());
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
	public PwdUpdate_res pwdUpdate(PwdUpdate_req req, TokenMappingRds token, TUserUsers user) throws Exception {
		String newPwd = req.getBody().getNew_pwd();//Md5Util.getSign4String(req.getBody().getNew_pwd(), "");
		Timestamp sysate = commonSrv.querySysdate();
		usersSrv.updatePassword(user.getUser_id(), newPwd, sysate);
		return new PwdUpdate_res();
	}

	public TUserUsers pwdUpdateValidate(PwdUpdate_req req, TokenMappingRds token) throws Exception{

		TUserUsers user = usersSrv.queryUser(token.getUserId());
		String oldPsw = req.getBody().getOld_pwd();//Md5Util.getSign4String(req.getBody().getOld_pwd(),"");
		if (!oldPsw.equals(user.getUser_pwd())) {
			throw new AppException(ResCodeAppEnum.USER_OLD_PWD.getCode());
		}
		return user;
	}

	public QueryFuzzy_res queryFuzzy(QueryFuzzy_req req, TokenMappingRds token) {
		QueryFuzzy_req.Param p = req.getBody().getParam();
		long count = req.getBody().getCount();
		String key = p.getSearch_key();
		long type = p.getSearch_type();
		
		List<QryUserInfoVO> userlist = usersSrv.queryUsersFuzzy(key, count, type);
		ArrayList<QueryFuzzy_res.Users> users = new ArrayList<QueryFuzzy_res.Users>();

		for (QryUserInfoVO pq : userlist) {

			QueryFuzzy_res.Users user = new QueryFuzzy_res.Users();
			user.setId(pq.getUser_id());
			user.setSamchat_id(pq.getUser_code());
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
		res.setCount(new Long(users.size()));
		res.setUsers(users);

		return res;
	}

	public void queryFuzzyValidate(QueryFuzzy_req req, TokenMappingRds token) {
	}

	public QueryAccurate_res queryAccurate(QueryAccurate_req req, TokenMappingRds token) throws Exception {

		QueryAccurate_req.Param p = req.getBody().getParam();
		String cellphone = null;
		if (p.getCellphone() != null) {
			cellphone = p.getCellphone().replaceAll("\\+| ", "");
		}
		String username = p.getUsername();
		String id = p.getUnique_id();
		Long type = p.getType();

		ArrayList<QueryAccurate_res.Users> users = new ArrayList<QueryAccurate_res.Users>();
		if (type == 1) {
			UserInfoRds uur = usersSrv.hgetUserInfoJsonObj(Long.parseLong(id));
			if (uur != null) {
				users = new ArrayList<QueryAccurate_res.Users>();
				QueryAccurate_res.Users user = new QueryAccurate_res.Users();
				user.setId(uur.getUser_id());
				user.setSamchat_id(uur.getUser_code());
				user.setUsername(uur.getUser_name());
				user.setCountrycode(uur.getCountry_code());
				user.setCellphone(uur.getPhone_no());
				user.setEmail(uur.getEmail());
				user.setAddress(uur.getAddress());
				user.setType(new Long(uur.getUser_type()));
				user.setLastupdate(uur.getState_date().getTime());

				QueryAccurate_res.Avatar avatar = new QueryAccurate_res.Avatar();
				user.setAvatar(avatar);
				avatar.setOrigin(uur.getAvatar_origin());
				avatar.setThumb(uur.getAvatar_thumb());

				QueryAccurate_res.Sam_pros_info pros = new QueryAccurate_res.Sam_pros_info();
				user.setSam_pros_info(pros);
				
				UserInfoProRds uupr = usersSrv.hgetUserInfoProsJsonObj(Long.parseLong(id));
				if (uupr != null) {
					pros.setCompany_name(uupr.getCompany_name());
					pros.setService_category(uupr.getService_category());
					pros.setService_description(uupr.getService_description());
					pros.setCountrycode(uupr.getCountry_code());
					pros.setPhone(uupr.getPhone_no());
					pros.setEmail(uupr.getEmail());
					pros.setAddress(uupr.getAddress());
				}
				users.add(user);
			}
		} else {
			List<QryUserInfoVO> userlist = usersSrv.queryUserAccurate(type, cellphone, username, id);

			for (QryUserInfoVO pq : userlist) {
				QueryAccurate_res.Users user = new QueryAccurate_res.Users();
				user.setId(pq.getUser_id());
				user.setSamchat_id(pq.getUser_code());
				user.setUsername(pq.getUser_name());
				user.setCountrycode(pq.getCountrycode());
				user.setCellphone(pq.getCellphone());
				user.setEmail(pq.getEmail());
				user.setAddress(pq.getAddress());
				user.setType(pq.getType());
				user.setLastupdate(pq.getLastupdate().getTime());

				QueryAccurate_res.Avatar avatar = new QueryAccurate_res.Avatar();
				user.setAvatar(avatar);
				avatar.setOrigin(pq.getOrigin());
				avatar.setThumb(pq.getThumb());

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
		}

		QueryAccurate_res res = new QueryAccurate_res();
		res.setCount(new Long(users.size()));
		res.setUsers(users);

		return res;
	}

	public void queryAccurateValidate(QueryAccurate_req req, TokenMappingRds token) {

	}

	public QueryGroup_res queryGroup(QueryGroup_req req, TokenMappingRds token) {
		QueryGroup_req.Param param = req.getBody().getParam();
		ArrayList<Long> userIds = param.getUnique_id();
		List<QryUserInfoVO> userlist = usersSrv.queryUsersGroup(userIds);

		ArrayList<QueryGroup_res.Users> users = new ArrayList<QueryGroup_res.Users>();

		for (QryUserInfoVO pq : userlist) {

			QueryGroup_res.Users user = new QueryGroup_res.Users();
			user.setId(pq.getUser_id());
			user.setSamchat_id(pq.getUser_code());
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
		res.setCount(new Long(users.size()));
		res.setUsers(users);

		return res;
	}

	public void queryGroupValidate(QueryGroup_req req, TokenMappingRds token) {
	}

	public QueryWithoutToken_res queryWithoutToken(QueryWithoutToken_req req) {
		QueryWithoutToken_req.Param param = req.getBody().getParam();
		String cellphone = param.getCellphone();
		String countrycode = param.getCountrycode();
		String userName = param.getUsername();
		long type = param.getType();

		List<TUserUsers> users = usersSrv.queryUserWithoutToken(type, countrycode, cellphone, userName);

		QueryWithoutToken_res res = new QueryWithoutToken_res();
		res.setCount(new Long(users.size()));

		return res;
	}

	public void queryWithoutTokenValidate(QueryWithoutToken_req req) {
	}

}
