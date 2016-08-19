package com.samchat.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.json.appserver.profile.AppkeyGet_req;
import com.samchat.common.beans.auto.json.appserver.profile.AppkeyGet_res;
import com.samchat.common.beans.auto.json.appserver.user.SignupCodeVerify_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.service.interfaces.IUsersSrv;

public class ProfileAction extends BaseAction {

	private static Logger log = Logger.getLogger(ProfileAction.class);

	@Autowired
	private IUsersSrv usersSrv;

	public AppkeyGet_res appkeyGet(AppkeyGet_req req) {
		AppkeyGet_res res = new AppkeyGet_res();
		res.setRet(Constant.SUCCESS);

		String appI = CommonUtil.getSysConfigStr("getui_appI");
		String appK = CommonUtil.getSysConfigStr("getui_appK");
		String appS = CommonUtil.getSysConfigStr("getui_appS");
		log.info("appI:" + appI + "---appK" + appI + "---appS:" + appS);

		res.setAppi(appI);
		res.setAppk(appK);
		res.setApps(appS);
		return res;
	}

	public void appkeyGetValidate(AppkeyGet_req req) {
		String token = req.getHeader().getToken();
		tokenIdentify(token);
	}
}
