package com.samchat.dao.db.interfaces;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.manual.db.QryUserInfoVO;

public interface IUserDbDao extends IBaseDbDao {

	public TUserUsers queryUserInfoByEmail(String email);

	public TUserUsers queryUserInfoByUserName(String userName);

	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode);

	public void insertUser(TUserUsers user, Timestamp sysdate);

	public void insertProUser(TUserProUsers proUser);

	public void updateUser(TUserUsers user);

	public void updateProUser(TUserProUsers user, Timestamp sysdate);

	public TUserProUsers queryProUser(long userId);

	public List<TUserUsers> queryUsers();

	public TUserUsers queryUser(long userId);
	
	public List<TUserUsers> queryUserByToken(String token);

	public List<QryUserInfoVO> queryUsersFuzzy(String key);

	public List<QryUserInfoVO> queryUsersGroup(List<Long> userIds);

	public List<QryUserInfoVO> queryUserAccurate(HashMap<String, Object> param);

	public List<TUserUsers> queryUserWithoutToken(long type, String countrycode, String cellphone, String userName);

}
