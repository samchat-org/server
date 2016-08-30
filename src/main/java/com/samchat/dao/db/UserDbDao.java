package com.samchat.dao.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsersExample;
import com.samchat.common.beans.auto.db.mapper.TUserProUsersMapper;
import com.samchat.common.beans.auto.db.mapper.TUserUsersMapper;
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.exceptions.AppException;
import com.samchat.dao.db.interfaces.IUserDbDao;

@Repository
public class UserDbDao extends BaseDbDao implements IUserDbDao {

	@Autowired
	private TUserUsersMapper userUsersMapper;
	@Autowired
	private TUserProUsersMapper userProUsersMapper;

	protected String getNamespace() {
		return "userSqlMapper";
	}

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

	public void insertUser(TUserUsers user) {
		userUsersMapper.insert(user);
	}

	public void insertProUser(TUserProUsers proUser) {
		userProUsersMapper.insert(proUser);
	}

	public void updateUser(TUserUsers user) {
		userUsersMapper.updateByPrimaryKeySelective(user);
	}

	public void updateProUser(TUserProUsers user) {
		userProUsersMapper.updateByPrimaryKeySelective(user);
	}

	public TUserProUsers queryProUser(long userId) {
		return userProUsersMapper.selectByPrimaryKey(userId);
	}

	public TUserUsers queryUser(long userId) {
		return userUsersMapper.selectByPrimaryKey(userId);
	}

	public List<TUserUsers> queryUsers() {
		return userUsersMapper.selectByExample(new TUserUsersExample());
	}

	public List<QryUserInfoVO> queryUsersFuzzy(String key) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("key", key);
		return this.executeSqlList("queryUsersFuzzy", p);
	}

	public List<QryUserInfoVO> queryUsersGroup(List<Long> userIds) {
		Map<String, Object> userIdsParam = new HashMap<String, Object>();
		userIdsParam.put("userIds", userIds);
		return this.executeSqlList("queryUsersGroup", userIdsParam);
	}

	public List<QryUserInfoVO> queryUserAccurate(HashMap<String, Object> param) {
		return this.executeSqlList("queryUserAccurate", param);
	}

	public List<TUserUsers> queryUserWithoutToken(long type, String countrycode, String cellphone, String userName) {
		TUserUsersExample uue = new TUserUsersExample();
		TUserUsersExample.Criteria ca = uue.createCriteria().andStateEqualTo(Constant.STATE_IN_USE);
		if (type == 0) {
			ca.andCountry_codeEqualTo(countrycode).andPhone_noEqualTo(cellphone);
		} else if (type == 2) {
			ca.andUser_nameEqualTo(userName);
		} else {
			throw new AppException(Constant.ERROR.PARAM_NONSUPPORT, "queryUserWithoutToken , type: " + type);
		}
		return userUsersMapper.selectByExample(uue);
	}

}
