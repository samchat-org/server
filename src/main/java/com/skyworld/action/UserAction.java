package com.skyworld.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.skyworld.beans.auto.db.entitybeans.TUserUsers;
import com.skyworld.beans.auto.json.appserver.user.RegisterCodeRequest_req;
import com.skyworld.beans.auto.json.appserver.user.RegisterCodeRequest_res;
import com.skyworld.beans.auto.json.appserver.user.Register_req;
import com.skyworld.beans.auto.json.appserver.user.Register_res;
import com.skyworld.common.AppException;
import com.skyworld.common.Constant;
import com.skyworld.service.interfaces.IUsersSrv;
import com.skyworld.utils.CacheUtil;
import com.skyworld.utils.CommonUtil;

public class UserAction extends BaseAction<Register_req> {

	@Autowired
	private IUsersSrv usersSrv;

	public RegisterCodeRequest_res registerCodeRequest(RegisterCodeRequest_req req) throws Exception {
		RegisterCodeRequest_req.Body body = req.getBody();
		String cellphone = body.getCellphone();
		String countryCode = body.getCountrycode();
		String registerCode = (Math.random() + "").substring(2, 8);
		
		int timeToIdle = CommonUtil.getSysConfigInt("register_code_time_to_idle");
 		CommonUtil.putRegisterCode(countryCode, cellphone, registerCode, timeToIdle, timeToIdle);
 		
 		RegisterCodeRequest_res res = new RegisterCodeRequest_res();
 		res.setRet(Constant.SUCCESS);
 		
		return res;
	}

	public void registerCodeRequestValidate(RegisterCodeRequest_req req) throws Exception{

		RegisterCodeRequest_req.Body body = req.getBody();

		String countryCode = body.getCountrycode();
		String cellphone = body.getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(body.getCellphone())) {
			throw new AppException(Constant.ERROR_PHONE_FORMAT_ILLEGAL);
		}
		TUserUsers userUsers = usersSrv.queryUserInfoByPhone(body.getCellphone(), body.getCountrycode());
		if (userUsers != null) {
			throw new AppException(Constant.ERROR_PHONEorUSERNAME_EXIST);
		}
 		if (CommonUtil.getRegisterCode(countryCode, cellphone) != null) {
 			throw new AppException(Constant.ERROR_REGISTER_CODE_FREQUENT);
		}
	}

	public Register_res register(Register_req req) throws Exception {

		return usersSrv.saveRegisterUserInfo(req);

	}

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
