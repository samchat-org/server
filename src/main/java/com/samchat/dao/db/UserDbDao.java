package com.samchat.dao.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.samchat.common.beans.auto.db.entitybeans.TUserProUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserProUsersExample;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsersExample;
import com.samchat.common.beans.auto.db.mapper.TUserProUsersMapper;
import com.samchat.common.beans.auto.db.mapper.TUserUsersMapper;
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.exceptions.SysException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.dao.db.interfaces.IUserDbDao;

@Repository
public class UserDbDao extends BaseDbDao implements IUserDbDao {

	private static Logger log = Logger.getLogger(UserDbDao.class);

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
		log.info("phoneNo:" + phoneNo + "--countrycode:" + countryCode);
		List<TUserUsers> luu = userUsersMapper.selectByExample(uue);
		log.info(luu.size());
		if (luu.size() > 0) {
			return luu.get(0);
		}
		return null;
	}

	public TUserUsers queryUserInfoByUsercode(String usercode) {
		TUserUsersExample uue = new TUserUsersExample();
		uue.createCriteria().andUser_codeIsNotNull().andUser_codeNotEqualTo("").andUser_codeEqualTo(usercode).andStateEqualTo(Constant.STATE_IN_USE);
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

	public void insertUser(TUserUsers user, Timestamp sysdate) {
		user.setState_date(sysdate);
		userUsersMapper.insert(user);
	}

	public void insertProUser(TUserProUsers proUser) {
 		userProUsersMapper.insert(proUser);
	}

	public void updateUser(TUserUsers user) {
 		userUsersMapper.updateByPrimaryKeySelective(user);
	}

	public void updateProUser(TUserProUsers user) {
 		TUserProUsersExample uue = new TUserProUsersExample();
		uue.createCriteria().andUser_idEqualTo(user.getUser_id()).andStateEqualTo(Constant.STATE_IN_USE);
		userProUsersMapper.updateByExampleSelective(user, uue);
	}

	public TUserProUsers queryProUser(long userId) {
		TUserProUsersExample uue = new TUserProUsersExample();
		uue.createCriteria().andUser_idEqualTo(userId).andStateEqualTo(Constant.STATE_IN_USE);
		List<TUserProUsers> prousers = userProUsersMapper.selectByExample(uue);
		if (prousers.size() > 0)
			return prousers.get(0);
		return null;
	}

	public TUserUsers queryUser(long userId) {
		return userUsersMapper.selectByPrimaryKey(userId);
	}
	
	public List<TUserUsers> queryUserByToken(String token) {
		TUserUsersExample uue = new TUserUsersExample();
		uue.createCriteria().andCur_tokenEqualTo(token).andStateEqualTo(Constant.STATE_IN_USE);
		return userUsersMapper.selectByExample(uue);
 	}

	public List<TUserUsers> queryUsers() {
		TUserUsersExample uue = new TUserUsersExample();
		uue.createCriteria().andUser_typeEqualTo(Constant.USER_TYPE_SERVICES).andStateEqualTo(Constant.STATE_IN_USE).andUser_nameNotLike("test%");
		return userUsersMapper.selectByExample(uue);
	}

	public List<QryUserInfoVO> queryUsersFuzzy(String key , long count, long type) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("key", key);
		p.put("type", new Long(type));
		int ps = getQueryUsersFuzzyPageSize();
		if(count < ps && count > 0){
			return new ArrayList<QryUserInfoVO>();
		}
		int curpage = ((int)count / ps) + 1;
		PageInfo pi = executePageSql("queryUsersFuzzy", p, curpage, ps);
		return pi.getList();
		
 	}
	
	private int getQueryUsersFuzzyPageSize(){
		int ps = Integer.parseInt(CommonUtil.getSysConfigStr(SysParamCodeDbEnum.PAGE_SIZE_QUERYUSERSFUZZY.getParamCode()));
		if(ps <= 0){
			throw new SysException("queryPageSize <= 0, error ");
		}
		return ps;
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
		}
		return userUsersMapper.selectByExample(uue);
	}
	
	public List<TUserUsers> querySamchatId(String samchatId){
		TUserUsersExample uue = new TUserUsersExample();
		TUserUsersExample.Criteria ca = uue.createCriteria().andStateEqualTo(Constant.STATE_IN_USE).andUser_codeEqualTo(samchatId);
		return userUsersMapper.selectByExample(uue);
	}

}
