package com.samchat.dao.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.auto.db.entitybeans.TOaFollowExample;
import com.samchat.common.beans.auto.db.mapper.TOaFollowMapper;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.dao.db.interfaces.IOfficialAccountDbDao;

@Repository
public class OfficialAccountDbDao extends BaseDbDao implements IOfficialAccountDbDao {

	@Autowired
	private TOaFollowMapper oaFollowMapper;

	protected String getNamespace() {
		return "oaSqlMapper";
	}

	public TOaFollow queryUserFollow(long userId, long userIdPros) {
		TOaFollowExample fe = new TOaFollowExample();
		fe.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(userIdPros)
				.andStateEqualTo(Constant.STATE_IN_USE);
		List<TOaFollow> ltf = oaFollowMapper.selectByExample(fe);
		if (ltf.size() > 0) {
			return ltf.get(0);
		}
		return null;
	}

	public void insertFollow(long userId, long userIdPros) {
		TOaFollow tf = new TOaFollow();
		tf.setUser_id(userId);
		tf.setUser_id_pro(userIdPros);
		tf.setBlock_tag(Constant.ADS_UNBLOCK);
		tf.setFavourite_tag(Constant.OA_UNFAVOURITE);
		tf.setState(Constant.STATE_IN_USE);
		tf.setState_date(this.querySysdate());
		oaFollowMapper.insert(tf);
	}

	public void deleteFollow(long userId, long userIdPros) {
		TOaFollowExample fe = new TOaFollowExample();
		fe.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(userIdPros)
				.andStateEqualTo(Constant.STATE_IN_USE);
		oaFollowMapper.deleteByExample(fe);
	}

	public void updateBlock(long userId, long userIdPros, byte block) {
		TOaFollowExample fe = new TOaFollowExample();
		fe.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(userIdPros)
				.andStateEqualTo(Constant.STATE_IN_USE);

		TOaFollow record = new TOaFollow();
		record.setBlock_tag(block);
		;
		oaFollowMapper.updateByExampleSelective(record, fe);
	}

	public void updateFavourite(long userId, long userIdPros, byte favourite) {
		TOaFollowExample fe = new TOaFollowExample();
		fe.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(userIdPros)
				.andStateEqualTo(Constant.STATE_IN_USE);

		TOaFollow record = new TOaFollow();
		record.setFavourite_tag(favourite);
		oaFollowMapper.updateByExampleSelective(record, fe);
	}

	public List<QryFollowVO> queryFollowList(long userId) {
		Map param = new HashMap();
		param.put("user_id", new Long(userId));
		return executeSqlList("queryFollowList", param);
	}
}
