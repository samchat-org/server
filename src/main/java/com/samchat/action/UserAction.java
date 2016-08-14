package com.samchat.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.user.RegisterCodeRequest_req;
import com.samchat.common.beans.auto.json.appserver.user.RegisterCodeRequest_res;
import com.samchat.common.beans.auto.json.appserver.user.Register_req;
import com.samchat.common.beans.auto.json.appserver.user.Register_res;
import com.samchat.common.beans.auto.json.appserver.user.SignupCodeVerify_req;
import com.samchat.common.beans.auto.json.appserver.user.SignupCodeVerify_res;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.TwilioUtil;
import com.samchat.service.interfaces.IUsersSrv;

public class UserAction extends BaseAction<Register_req> {
	
	private static Logger log = Logger.getLogger(UserAction.class);
	
	@Autowired
	private IUsersSrv usersSrv;
	/**
	 * 注册验证码验证
	 * @param req
	 * @return
	 */
	public SignupCodeVerify_res signupCodeVerify(SignupCodeVerify_req req){
		SignupCodeVerify_res res = new SignupCodeVerify_res();
		res.setRet(Constant.SUCCESS);
  		return res;
	}
	
	/**
	 * 注册验证码验证 信息校验
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
		String registerCode = CommonUtil.getRegisterCode(countryCode, cellphone);
		if(registerCode == null){
			throw new AppException(Constant.ERROR_REGISTER_CODE_EXPIRED);
		}
		if(!registerCode.equals(body.getVerifycode())){
			throw new AppException(Constant.ERROR_REGISTER_CODE);
		}
	}
	
	/**
	 * 注册验证码申请
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public RegisterCodeRequest_res registerCodeRequest(RegisterCodeRequest_req req) throws Exception {
		RegisterCodeRequest_req.Body body = req.getBody();
		String cellphone = body.getCellphone();
		String countryCode = body.getCountrycode();
		String registerCode = (Math.random() + "").substring(2, 8);
		log.info("countryCode:" + countryCode + "--" + "cellphone:" + cellphone + "--registerCode:" + registerCode);
		
		int timeToIdle = CommonUtil.getSysConfigInt("register_code_time_to_idle");
		CommonUtil.putRegisterCode(countryCode, cellphone, registerCode, timeToIdle, timeToIdle);
		
		String smstpl = CommonUtil.getSysConfigStr(Constant.TWILLO_VERIFICATION_CODE_SMS_TEMPLETE);
		String smsContent = smstpl.replaceAll(Constant.TWILLO_VERIFICATION_CODE, registerCode);
		TwilioUtil.sendSms(CommonUtil.getE164PhoneNo(countryCode, cellphone), CommonUtil.getSysConfigStr("twillo_phone_no"), smsContent);

		RegisterCodeRequest_res res = new RegisterCodeRequest_res();
		res.setRet(Constant.SUCCESS);

		return res;
	}
	
	/**
	 * 注册验证码申请 信息校验
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
		if (CommonUtil.getRegisterCode(countryCode, cellphone) != null) {
			throw new AppException(Constant.ERROR_REGISTER_CODE_FREQUENT);
		}
	}

	/**
	 * 注册请求
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public Register_res register(Register_req req) throws Exception {

		return usersSrv.saveRegisterUserInfo(req);

	}

	/**
	 * 注册请求 信息校验
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

}
