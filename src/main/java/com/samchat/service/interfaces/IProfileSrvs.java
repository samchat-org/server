package com.samchat.service.interfaces;

import java.sql.Timestamp;

import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_req;

public interface IProfileSrvs extends IBaseSrvs{
	
	public long updateProfile(ProfileUpdate_req req, long userId, Timestamp sysdate);

}
