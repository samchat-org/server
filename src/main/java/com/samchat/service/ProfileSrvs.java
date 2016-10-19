package com.samchat.service;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_req;
import com.samchat.common.beans.manual.common.SysdateObjBean;
import com.samchat.common.beans.manual.json.redis.UserInfoProRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.enums.Constant;
import com.samchat.dao.db.interfaces.IUserDbDao;
import com.samchat.service.interfaces.IProfileSrvs;

@Service
public class ProfileSrvs extends BaseSrvs implements IProfileSrvs {

	private static Logger log = Logger.getLogger(ProfileSrvs.class);

	@Autowired
	private IUserDbDao userDbDao;

	public void updateProfile(ProfileUpdate_req req, long userId, SysdateObjBean sysdate) throws Exception{
		ProfileUpdate_req.User user = req.getBody().getUser();
		
		TUserUsers tuser = userDbDao.queryUser(userId);
		tuser.setUser_id(userId);
		tuser.setCountry_code(user.getCountrycode());
		tuser.setPhone_no(user.getCellphone());
		tuser.setEmail(user.getEmail());
		tuser.setAddress(user.getAddress());
		tuser.setState_date(sysdate.getNow());
		userDbDao.updateUser(tuser);
		
		UserInfoRds uur = new UserInfoRds();
		uur.setNowVersion(sysdate.getNowVersion());
		PropertyUtils.copyProperties(uur, tuser);
		hsetUserInfoJsonObj(userId, uur);
		
 		if(tuser.getUser_type() == Constant.USER_TYPE_SERVICES){
			TUserProUsers tuserPros = userDbDao.queryProUser(userId);
			if(tuserPros != null){
				ProfileUpdate_req.Sam_pros_info proInfo = user.getSam_pros_info();
				tuserPros.setUser_id(userId);
				tuserPros.setCompany_name(proInfo.getCompany_name());
				tuserPros.setService_category(proInfo.getService_category());
				tuserPros.setService_description(proInfo.getService_description());
				tuserPros.setCountry_code(proInfo.getCountrycode());
				tuserPros.setPhone_no(proInfo.getPhone());
				tuserPros.setEmail(proInfo.getEmail());
				tuserPros.setAddress(proInfo.getAddress());
				tuserPros.setState_date(sysdate.getNow());
				userDbDao.updateProUser(tuserPros, sysdate.getNow());
				
				UserInfoProRds uurp = new UserInfoProRds();
				uur.setNowVersion(sysdate.getNowVersion());
				PropertyUtils.copyProperties(uurp, tuserPros);
				hsetUserInfoProsJsonObj(userId, uurp);
			}
		}
	}

	public void niSendMessage() {

	}
}
