package com.samchat.service.interfaces;

import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_req;

public interface IProfileSrv {
	
	public long updateProfile(ProfileUpdate_req req, long userId);

}
