package com.samchat.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.profile.AppkeyGet_req;
import com.samchat.common.beans.auto.json.appserver.profile.AppkeyGet_res;
import com.samchat.common.beans.auto.json.appserver.profile.AvatarUpdate_req;
import com.samchat.common.beans.auto.json.appserver.profile.AvatarUpdate_res;
import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_req;
import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.service.interfaces.IProfileSrv;
import com.samchat.service.interfaces.IUsersSrv;

public class ProfileAction extends BaseAction {

	private static Logger log = Logger.getLogger(ProfileAction.class);

	@Autowired
	private IUsersSrv usersSrv;
	@Autowired
	private IProfileSrv profileSrv;

	public AppkeyGet_res appkeyGet(AppkeyGet_req req, TokenRds token) {
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

	public void appkeyGetValidate(AppkeyGet_req req, TokenRds token) {
	}

	public ProfileUpdate_res profileUpdate(ProfileUpdate_req req, TokenRds token, TUserUsers updatedUser) {
		String tokenStr = req.getHeader().getToken();
		
		long lastupdate = profileSrv.updateProfile(req, token.getUserId());
		
		if(updatedUser.getCountry_code() != null){
			
			log.info("cancel userinfo : " + token.getCountryCode());
 			usersSrv.cancelUserInfoIntoRedis(token.getCountryCode(), token.getCellPhone());
 			
 			String newcountrycode = updatedUser.getCountry_code();
 			String newcellphone = updatedUser.getPhone_no();
 			
 			log.info("set userinfo : " + newcellphone);
			usersSrv.setUserInfoIntoRedis(newcountrycode, newcellphone, tokenStr);
			
 			usersSrv.resetToken(null, newcountrycode, newcellphone, tokenStr);
			
		}
 		ProfileUpdate_res res = new ProfileUpdate_res();
		ProfileUpdate_res.User userRes = new ProfileUpdate_res.User();
		res.setUser(userRes);
 		userRes.setLastupdate(lastupdate);

		return res;
	}

	public TUserUsers profileUpdateValidate(ProfileUpdate_req req, TokenRds token) {

		String countrycode = token.getCountryCode();
		String cellphone = token.getCellPhone();

		ProfileUpdate_req.User user = req.getBody().getUser();
		String countrycodeOpt = user.getCountrycode();
		String cellphoneOpt = user.getCellphone();

		if (countrycodeOpt == null) {
			countrycodeOpt = countrycode;
		}
		if (cellphoneOpt == null) {
			cellphoneOpt = cellphone;
		}
		TUserUsers u = new TUserUsers();;
		if (countrycodeOpt != countrycode || cellphoneOpt != cellphone) {
			u = usersSrv.queryUserInfoByPhone(cellphoneOpt, countrycodeOpt);
			if (u != null) {
				throw new AppException(Constant.ERROR.PHONEorUSERNAME_EXIST, "countrycode:" + countrycodeOpt
						+ "--cellphone:" + cellphoneOpt);
			}
 			u.setCountry_code(countrycodeOpt);
			u.setPhone_no(cellphoneOpt);
		}
		return u;
	}
	
	public AvatarUpdate_res avatarUpdate(AvatarUpdate_req req, TokenRds token){
		
		AvatarUpdate_req.Avatar avatar = req.getBody().getAvatar();
		String origin = avatar.getOrigin();
		String thumb = avatar.getThumb();
		long userId = token.getUserId();
		
		long lastupdate = usersSrv.updateAvatar(origin, thumb, userId);
		
		AvatarUpdate_res res = new AvatarUpdate_res();
		AvatarUpdate_res.User user = new AvatarUpdate_res.User();
		res.setUser(user);
		user.setLastupdate(lastupdate);
		
		return res;
	}
	
	public void avatarUpdateValidate(AvatarUpdate_req req, TokenRds token){
 	}
}
