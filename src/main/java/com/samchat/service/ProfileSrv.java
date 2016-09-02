package com.samchat.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_req;
import com.samchat.dao.db.interfaces.IUserDbDao;
import com.samchat.service.interfaces.IProfileSrv;

@Service
public class ProfileSrv implements IProfileSrv {

	private static Logger log = Logger.getLogger(ProfileSrv.class);
	
	@Autowired
	private IUserDbDao userDbDao;
	
	public long updateProfile(ProfileUpdate_req req, long userId){
		
		ProfileUpdate_req.User user = req.getBody().getUser();
	  	
		TUserUsers u = new TUserUsers();
		u.setUser_id(userId);
		u.setCountry_code(user.getCountrycode());
		u.setPhone_no(user.getCellphone());
		u.setEmail(user.getEmail());
		u.setAddress(user.getAddress());
		
		ProfileUpdate_req.Sam_pros_info proInfo = user.getSam_pros_info();
		TUserProUsers uo = new TUserProUsers();
		uo.setUser_id(userId);
		uo.setCompany_name(proInfo.getCompany_name());
		uo.setService_category(proInfo.getService_category());
		uo.setService_description(proInfo.getService_description());
		uo.setCountry_code(proInfo.getCountrycode());
		uo.setPhone_no(proInfo.getPhone());
		uo.setEmail(proInfo.getEmail());
		uo.setAddress(proInfo.getAddress());
		
		userDbDao.updateUser(u);
		userDbDao.updateProUser(uo);
		
		return u.getState_date().getTime();
	}
}
