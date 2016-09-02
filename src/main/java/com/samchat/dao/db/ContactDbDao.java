package com.samchat.dao.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.action.OfficialAccountAction;
import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TContactProUser;
import com.samchat.common.beans.auto.db.entitybeans.TContactProUserExample;
import com.samchat.common.beans.auto.db.entitybeans.TContactUser;
import com.samchat.common.beans.auto.db.entitybeans.TContactUserExample;
import com.samchat.common.beans.auto.db.mapper.TContactProUserMapper;
import com.samchat.common.beans.auto.db.mapper.TContactUserMapper;
import com.samchat.common.beans.manual.db.QryContactVO;
import com.samchat.dao.db.interfaces.IContactDbDao;

@Repository
public class ContactDbDao extends BaseDbDao implements IContactDbDao {
	
	private static Logger log = Logger.getLogger(ContactDbDao.class);
	
	@Autowired
	private TContactUserMapper contactUserMapper;

	@Autowired
	private TContactProUserMapper contactProUserMapper;

	protected String getNamespace() {
		return "contactSqlMapper";
	}

	public void addContactUser(long userId, long proUserId) {
		TContactUserExample cue = new TContactUserExample();
		cue.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(proUserId)
				.andStateEqualTo(Constant.STATE_IN_USE);
		List<TContactUser> lst = contactUserMapper.selectByExample(cue);
		if (lst.size() > 0) {
			return;
		}
		TContactUser cu = new TContactUser();
		cu.setUser_id(userId);
		cu.setUser_id_pro(proUserId);
		cu.setState(Constant.STATE_IN_USE);
		cu.setState_date(querySysdate());
		contactUserMapper.insert(cu);
	}

	public void addContactProUser(long proUserId, long userId) {
		TContactProUserExample cue = new TContactProUserExample();
		cue.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(proUserId)
				.andStateEqualTo(Constant.STATE_IN_USE);
		List<TContactProUser> lst = contactProUserMapper.selectByExample(cue);
		if (lst.size() > 0) {
			return;
		}
		TContactProUser cu = new TContactProUser();
		cu.setUser_id(userId);
		cu.setUser_id_pro(proUserId);
		cu.setState(Constant.STATE_IN_USE);
		cu.setState_date(querySysdate());
		contactProUserMapper.insert(cu);
	}

	public void deleteContactUser(long userId, long proUserId) {
		log.info("user_id:" + userId + "--proUserId:" + proUserId);
		TContactUserExample cue = new TContactUserExample();
		cue.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(proUserId)
				.andStateEqualTo(Constant.STATE_IN_USE);
		contactUserMapper.deleteByExample(cue);
	}

	public void deleteContactProUser(long proUserId, long userId) {
		TContactProUserExample cue = new TContactProUserExample();
		cue.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(proUserId)
				.andStateEqualTo(Constant.STATE_IN_USE);
		contactProUserMapper.deleteByExample(cue);
	}

	public List<QryContactVO> queryContactUserList(long userId) {
		Map<String, Long> param = new HashMap<String, Long> ();
		param.put("user_id", new Long(userId));
		return this.executeSqlList("queryContactUserList", param);
	}

	public List<QryContactVO> queryContactProUserList(long proUserId) {
		Map<String, Long> param = new HashMap<String, Long> ();
		param.put("user_id_pro", new Long(proUserId));
		return this.executeSqlList("queryContactProUserList", param);
	}

}
