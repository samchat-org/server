package com.samchat.service.interfaces;

import java.sql.Timestamp;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.manual.common.SysdateObjBean;
import com.samchat.common.beans.manual.json.redis.TokenValRds;
import com.samchat.common.beans.manual.json.redis.UserInfoProRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;

public interface IBaseSrvs {
 		public void hsetUserInfoJsonObjRedis(long userId, String filed, Object uif) throws Exception ;
		public void hsetUserInfoJsonObj(long userId, UserInfoRds uur) ;
		public void hsetUserInfoProsJsonObj(long userId, UserInfoProRds uur) ;
		public void hsetUserInfoTokenJsonObj(long userId, TokenValRds tvr) ;
		public <T> T hgetUserInfoJsonObjRedis(long userId, String field, Class<T> clazz) throws Exception ;
		public UserInfoRds hgetUserInfoJsonObj(long userId) throws Exception ;
		public UserInfoProRds hgetUserInfoProsJsonObj(long userId) throws Exception ;
		public TokenValRds hgetUserInfoTokenJsonObj(long userId) throws Exception ;
		public Timestamp querySysdate() ;
		public SysdateObjBean querySysdateObj() throws Exception ;
		public void hsetUserInfoStrRedis(long userId, String filed, String uif) throws Exception;
 		public String hgetUserInfoStrRedis(long userId, String field) throws Exception;
		public String hgetUserInfoClientId(long userId);
		public String hgetUserInfoCustomerListDate(long userId);
 	 	public String hgetUserInfoServicerListDate(long userId); 
	 	public String hgetUserInfoFollowListDate(long userId);
	 	public void hsetUserInfoCustomerListDate(long userId, String date);
	 	public void hsetUserInfoServicerListDate(long userId, String date);
	 	public void hsetUserInfoFollowListDate(long userId, String date);
	 	public void hsetUserInfoProsTokenJsonObj(long userId, TokenValRds tvr);
	 	public TokenValRds hgetUserInfoProsTokenJsonObj(long userId);
	 	public TUserUsers updateUserInfo_master(TUserUsers userDb, SysdateObjBean sysdate) throws Exception;
	 	public void updateUserInfoPro_master(TUserProUsers userProDb, SysdateObjBean sysdate) throws Exception;
}
