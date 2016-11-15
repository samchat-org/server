package com.samchat.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_req;
import com.samchat.common.beans.auto.json.appserver.profile.UpdateQuestionNotify_req;
import com.samchat.common.beans.manual.common.SysdateObjBean;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.utils.CacheUtil;
import com.samchat.common.utils.CommonUtil;
import com.samchat.service.interfaces.IProfileSrvs;

@Service
public class ProfileSrvs extends BaseSrvs implements IProfileSrvs {

	private static Logger log = Logger.getLogger(ProfileSrvs.class);
	
	public void updateQuestionNotify_master(UpdateQuestionNotify_req req , long userId, SysdateObjBean sysdate) throws Exception{
		TUserUsers tuser = new TUserUsers();
		tuser.setUser_id(userId);
		tuser.setQuestion_notify(new Byte(req.getBody().getQuestion_notify().toString()));
		tuser.setState_date(sysdate.getNow());
		updateUserInfo_master(tuser, sysdate);
	}

	public void updateProfile_master(ProfileUpdate_req req, long userId, SysdateObjBean sysdate) throws Exception{
		ProfileUpdate_req.User user = req.getBody().getUser();
		
		// will update redis, so query user .
		TUserUsers tuser = new TUserUsers();
		tuser.setUser_id(userId);
		tuser.setEmail(user.getEmail());
		
		ProfileUpdate_req.Location userlocation =  user.getLocation();
		if(userlocation != null){
			tuser.setPlace_id(userlocation.getPlace_id());
			tuser.setAddress(userlocation.getAddress());
			ProfileUpdate_req.Location_info userlocationinfo = userlocation.getLocation_info();
			if(userlocationinfo != null){
				tuser.setLatitude(userlocationinfo.getLatitude());
				tuser.setLongitude(userlocationinfo.getLongitude());
			}
		}
		tuser.setState_date(sysdate.getNow());
		TUserUsers tuserfull = updateUserInfo_master(tuser, sysdate);
		
		ProfileUpdate_req.Sam_pros_info proInfo = user.getSam_pros_info();
		if(proInfo != null){
	 		if(tuserfull.getUser_type() == Constant.USER_TYPE_SERVICES){
				TUserProUsers tuserPros = new TUserProUsers();
				tuserPros.setUser_id(userId);
				tuserPros.setCompany_name(proInfo.getCompany_name());
				tuserPros.setService_category(proInfo.getService_category());
				tuserPros.setService_description(proInfo.getService_description());
				tuserPros.setCountry_code(proInfo.getCountrycode());
				tuserPros.setPhone_no(proInfo.getPhone());
				tuserPros.setEmail(proInfo.getEmail());
				
				ProfileUpdate_req.Location locationPros = proInfo.getLocation();
				if(locationPros != null){
					tuserPros.setAddress(locationPros.getAddress());
					tuserPros.setPlace_id(locationPros.getPlace_id());
					ProfileUpdate_req.Location_info locationinfoPros = locationPros.getLocation_info();
					if(locationinfoPros != null){
						tuserPros.setLatitude(locationinfoPros.getLatitude());
						tuserPros.setLongitude(locationinfoPros.getLongitude());
					}
				}
				tuserPros.setState_date(sysdate.getNow());
				updateUserInfoPro_master(tuserPros, sysdate);
			
			}
		}
	}
	
	public void putEditCellPhoneVerificationCode(String countryCode, String cellPhone, String verificationCode) {
		int timeToIdle = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.EDIT_CELL_PHONE_CODE_TIME_TO_IDLE.getParamCode());
		String keystr = CacheUtil.getEditCellPhoneCacheKey(countryCode, cellPhone);
		userRedisDao.set(keystr, verificationCode, timeToIdle);
	}
	
	public void putEditCellPhoneVerificationCodeCtrl(String countryCode, String cellPhone) {
		int timeToIdle = CommonUtil.getSysConfigInt(SysParamCodeDbEnum.EDIT_CELL_PHONE_CODE_CTRL_TIME_TO_IDLE.getParamCode());
		String keystr = CacheUtil.getEditCellPhoneCtrlCacheKey(countryCode, cellPhone);
		userRedisDao.set(keystr, "0", timeToIdle);
	}
 
	public String getEditCellPhoneVerificationCode(String countryCode, String cellPhone) {
		String keystr = CacheUtil.getEditCellPhoneCacheKey(countryCode, cellPhone);
		return userRedisDao.get(keystr);
	}
	
	public String getEditCellPhoneVerificationCodeCtrl(String countryCode, String cellPhone) {
		String keystr = CacheUtil.getEditCellPhoneCtrlCacheKey(countryCode, cellPhone);
		return userRedisDao.get(keystr);
	}
	
	public void updatePhoneNo_master(long userId, String countryCode, String phoneNo, SysdateObjBean sysdate) throws Exception{
		TUserUsers tuser = new TUserUsers();
		tuser.setUser_id(userId);
		tuser.setCountry_code(countryCode);
		tuser.setPhone_no(phoneNo);
		tuser.setState_date(sysdate.getNow());
		updateUserInfo_master(tuser, sysdate);
	}

	public void niSendMessage() {

	}
}
