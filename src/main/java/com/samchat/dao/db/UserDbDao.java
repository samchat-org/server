package com.samchat.dao.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsersExample;
import com.samchat.common.beans.auto.db.mapper.TUserProUsersMapper;
import com.samchat.common.beans.auto.db.mapper.TUserUsersMapper;
import com.samchat.dao.db.interfaces.IUserDbDao;

@Repository
public class UserDbDao extends BaseDbDao implements IUserDbDao{
	
	@Autowired
	private TUserUsersMapper userUsersMapper;
	@Autowired
	private TUserProUsersMapper userProUsersMapper;

	public TUserUsers queryUserInfoByPhone(String phoneNo, String countryCode) {
		TUserUsersExample uue = new TUserUsersExample();
		uue.createCriteria().andPhone_noEqualTo(phoneNo).andCountry_codeEqualTo(countryCode)
				.andStateEqualTo(Constant.STATE_IN_USE);
		List<TUserUsers> luu = userUsersMapper.selectByExample(uue);
		if (luu.size() > 0) {
			return luu.get(0);
		}
		return null;
	}

	public TUserUsers queryUserInfoByUserName(String userName) {
		TUserUsersExample uue = new TUserUsersExample();
		uue.createCriteria().andUser_nameEqualTo(userName).andStateEqualTo(Constant.STATE_IN_USE);
		List<TUserUsers> luu = userUsersMapper.selectByExample(uue);
		if (luu.size() > 0) {
			return luu.get(0);
		}
		return null;
	}

	public TUserUsers queryUserInfoByEmail(String email) {
		TUserUsersExample uue = new TUserUsersExample();
		uue.createCriteria().andEmailEqualTo(email).andStateEqualTo(Constant.STATE_IN_USE);
		List<TUserUsers> luu = userUsersMapper.selectByExample(uue);
		if (luu.size() > 0) {
			return luu.get(0);
		}
		return null;
	}
	
	public void insertUser(TUserUsers user){
		userUsersMapper.insert(user);
	}
	
	public void insertProUser(TUserProUsers proUser){
		userProUsersMapper.insert(proUser);
	}
	
	public void updateUser(TUserUsers user){
		userUsersMapper.updateByPrimaryKeySelective(user);	
	}
	
	public void updateProUser(TUserProUsers user){
		userProUsersMapper.updateByPrimaryKeySelective(user);	
	}
}
