package com.skyworld.service;

import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyworld.beans.auto.db.entitybeans.TUserUsers;
import com.skyworld.beans.auto.db.entitybeans.TUserUsersExample;
import com.skyworld.beans.auto.db.mapper.TUserUsersMapper;
import com.skyworld.beans.auto.json.appserver.user.Register_req;
import com.skyworld.beans.auto.json.appserver.user.Register_res;
import com.skyworld.common.Constant;
import com.skyworld.service.interfaces.IUsersSrv;
import com.skyworld.utils.CommonUtil;
import com.skyworld.utils.niUtils.NiUtil;
import com.sun.jndi.ldap.Connection;

@Service
public class UsersSrv extends BaseSrv implements IUsersSrv {

	@Autowired
	private TUserUsersMapper userUsersMapper;

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

	public Register_res saveRegisterUserInfo(Register_req req) throws Exception{
		
		Timestamp sysdate = querySysdate();
		long time = sysdate.getTime();
		
		Register_req.Body body = req.getBody();
		String phoneNo = body.getCellphone();
		String countryCode = body.getCountrycode();
		String userName = body.getUsername();
		String pwd = CommonUtil.encryptStr3Des(body.getPwd());
		String deviceId = body.getDeviceid();
		
 		int timeToIdle = CommonUtil.getSysConfigInt("token_failure_interval");
 		String token = CommonUtil.getAddedToken(phoneNo, time, deviceId, timeToIdle * 60, Integer.MAX_VALUE);

		TUserUsers uu = new TUserUsers();
		uu.setPro_user_id(0L);
		uu.setUser_type(Constant.USER_TYPE_CUSTOMER);
		uu.setPhone_no(phoneNo);
		uu.setCountry_code(countryCode);
		uu.setUser_name(userName);
		uu.setUser_pwd(pwd);
		uu.setState(Constant.STATE_IN_USE);
		uu.setState_date(sysdate);
		uu.setCreate_date(sysdate);
		uu.setCur_device_id(deviceId);
		uu.setCur_token(token);
		userUsersMapper.insert(uu);

		TUserUsersExample uue = new TUserUsersExample();
		uue.createCriteria().andPhone_noEqualTo(phoneNo).andCountry_codeEqualTo(countryCode)
				.andStateEqualTo(Constant.STATE_IN_USE);
		List<TUserUsers> luu = userUsersMapper.selectByExample(uue);
		if(luu.size() == 0){
			throw new RuntimeException("select register info failed, phoneNo:" +phoneNo + "--countryCode:" + countryCode);
		}
		TUserUsers u =  luu.get(0);	
 		
 		Map<String, String> register = new HashMap<String,String>();
		register.put("accid", String.valueOf(u.getUser_id()));
		register.put("name", userName);
		register.put("token", token);
		NiUtil.createAction(register, sysdate);
		
 		Register_res res = new Register_res();
		res.setRet(Constant.SUCCESS);
		
 		res.setToken(token);
		Register_res.User user = new Register_res.User();
 		user.setId(u.getUser_id());
  		user.setLastupdate(time);
 		res.setUser(user);
 		return res;
	}
	
	public void niPostService(){
		
	}
}
