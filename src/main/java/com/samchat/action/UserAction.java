package com.samchat.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TSysConfigs;
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
import com.samchat.common.beans.manual.common.SysdateObjBean;
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.redis.UserInfoProRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.enums.cache.UserInfoFieldRdsEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CacheUtil;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.Md5Util;
import com.samchat.common.utils.TwilioUtil;
import com.samchat.service.interfaces.ICommonSrvm;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class UserAction extends BaseAction {

	private static Logger log = Logger.getLogger(UserAction.class);

	@Autowired
	private IUsersSrvs usersSrv;

	@Autowired
	private ICommonSrvs commonSrv;

	@Autowired
	private ICommonSrvm commonSrvm;


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

		int timeToIdle = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.USER_REGISTER_CODE_TIME_TO_IDLE.getParamCode());

		registerCode = "1234";// dev_m
		String smstpl = CommonUtil
				.getSysConfigStr(SysParamCodeDbEnum.TWILIO_VERIFICATION_REGISTER_CODE_SMS_TEMPLETE.getParamCode());
		String smsContent = smstpl.replaceAll(Constant.TWILLO_VERIFICATION_CODE, registerCode);

		String twilloPhoneNo = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.TWILIO_PHONE_NO.getParamCode());
		TwilioUtil.sendSms(CommonUtil.getE164PhoneNo(countrycode, cellphone), twilloPhoneNo, smsContent);
		usersSrv.putRegisterCode(countrycode, cellphone, registerCode, timeToIdle);
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
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone(cellphone, countryCode);
		if (userUsers != null) {
			throw new AppException(ResCodeAppEnum.PHONEorUSERNAME_EXIST.getCode());
		}
		if (usersSrv.getRegisterCode(countryCode, cellphone) != null) {
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE_FREQUENT.getCode());
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
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone(cellphone, countryCode);
		if (userUsers != null) {
			throw new AppException(ResCodeAppEnum.PHONEorUSERNAME_EXIST.getCode());
		}
		String registerCode = usersSrv.getRegisterCode(countryCode, cellphone);
		if (registerCode == null) {
			throw new AppException(ResCodeAppEnum.REGISTER_CODE_EXPIRED.getCode());
		}
		if (!registerCode.equals(body.getVerifycode())) {
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE.getCode());
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
		Register_res res = usersSrv.saveRegisterUserInfo(req, sysdate);

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

		if (!CommonUtil.phoneNoFormatValidate(body.getCellphone())) {
			throw new AppException(ResCodeAppEnum.PHONE_FORMAT_ILLEGAL.getCode());
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone(body.getCellphone(), body.getCountrycode());
		if (userUsers == null) {
			userUsers = usersSrv.queryUserInfoByUserName(body.getUsername());
		}
		if (userUsers != null) {
			throw new AppException(ResCodeAppEnum.PHONEorUSERNAME_EXIST.getCode());
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
		Login_res res = usersSrv.saveLoginUserInfo(req, user, sysdate);
		
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
		String account = body.getAccount();
		String countryCode = body.getCountrycode();
		String pwd = Md5Util.getSign4String(body.getPwd(), "");

		TUserUsers user = usersSrv.queryUserInfoByPhone(account, countryCode);
		if (user == null) {
			user = usersSrv.queryUserInfoByUserName(account);
		}
		if (user == null) {
			throw new AppException(ResCodeAppEnum.USER_NOT_EXIST.getCode());
		}
		if (!user.getUser_pwd().equals(pwd)) {
			throw new AppException(ResCodeAppEnum.USER_PWD.getCode());
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
		usersSrv.saveProsUserInfo(req, user, sysdate);

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
		userRet.setLastupdate(sysdate.getNow().getTime());
		userRet.setAvatar(avatar);

		res.setUser(userRet);

		return res;
	}

	public TUserUsers createSamProsValidate(CreateSamPros_req req, TokenMappingRds token) {

		TUserUsers user = usersSrv.queryUser(token.getUserId());
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
		String verificationCode = CommonUtil.getRadom(4);
		verificationCode = "1234";
		int timeToIdle = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.USER_FIND_PASSWORD_CODE_TIME_TO_IDLE.getParamCode());
		usersSrv.putFindpasswordVerificationCode(countryCode, cellPhone, verificationCode, timeToIdle);

		String smstpl = CommonUtil
				.getSysConfigStr(SysParamCodeDbEnum.TWILIO_VERIFICATION_FINDPWD_CODE_SMS_TEMPLETE.getParamCode());
		String smsContent = smstpl.replaceAll(Constant.TWILLO_VERIFICATION_CODE, verificationCode);

		TwilioUtil.sendSms(countryCode, cellPhone, smsContent);

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
		String code = usersSrv.getFindpasswordVerificationCode(countryCode, cellPhone);
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
		String newPwd = Md5Util.getSign4String(req.getBody().getNew_pwd(), "");
		Timestamp sysate = commonSrv.querySysdate();
		usersSrv.updatePassword(user.getUser_id(), newPwd, sysate);
		return new PwdUpdate_res();
	}

	public TUserUsers pwdUpdateValidate(PwdUpdate_req req, TokenMappingRds token) {

		TUserUsers user = usersSrv.queryUser(token.getUserId());
		if (req.getBody().getOld_pwd().equals(user.getUser_pwd())) {
			throw new AppException(ResCodeAppEnum.USER_OLD_PWD.getCode());
		}
		return user;
	}

	public QueryFuzzy_res queryFuzzy(QueryFuzzy_req req, TokenMappingRds token) {
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
				user.setUsername(uur.getUser_name());
				user.setCountrycode(uur.getCountry_code());
				user.setCellphone(uur.getPhone_no());
				user.setEmail(uur.getEmail());
				user.setAddress(uur.getAddress());
				user.setType(uur.getUser_type());
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
		res.setCount(users.size());
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
		res.setCount(users.size());

		return res;
	}

	public void queryWithoutTokenValidate(QueryWithoutToken_req req) {
	}

}
