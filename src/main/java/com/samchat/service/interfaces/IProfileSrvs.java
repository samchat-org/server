package com.samchat.service.interfaces;

import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_req;
import com.samchat.common.beans.manual.common.SysdateObjBean;

public interface IProfileSrvs extends IBaseSrvs{
	
	public void updateProfile_master(ProfileUpdate_req req, long userId, SysdateObjBean sysdate) throws Exception;
	
	public void putEditCellPhoneVerificationCode(String countryCode, String cellPhone, String verificationCode);

	public String getEditCellPhoneVerificationCode(String countryCode, String cellPhone);
	
	public void updatePhoneNo_master(long userId, String countryCode, String phoneNo, SysdateObjBean sysdate) throws Exception;
	
	public String getEditCellPhoneVerificationCodeCtrl(String countryCode, String cellPhone);
	
	public void putEditCellPhoneVerificationCodeCtrl(String countryCode, String cellPhone);
}
