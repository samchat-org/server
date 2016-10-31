package com.samchat.service.interfaces;

import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_req;
import com.samchat.common.beans.manual.common.SysdateObjBean;

public interface IProfileSrvs extends IBaseSrvs{
	
	public void updateProfile_master(ProfileUpdate_req req, long userId, SysdateObjBean sysdate) throws Exception;

}
