package com.samchat.dao.db.interfaces;

import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;

public interface IUserDbDao extends IBaseDbDao{
	
	public TUserUsers queryUserInfoByEmail(String email);

	public TUserUsers queryUserInfoByUserName(String userName);

	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode);
	
	public void insertUser(TUserUsers user);
	
	public void insertProUser(TUserProUsers proUser);
	
	public void updateUser(TUserUsers user);
	
	public void updateProUser(TUserProUsers user);
	
	public TUserProUsers queryProUser(long userId);
	
	public List<TUserUsers> queryUsers();
	
	public TUserUsers queryUser(long userId);
	
}
