package com.samchat.dao.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.auto.db.entitybeans.TOaFollowExample;
import com.samchat.common.beans.auto.db.mapper.TOaFollowMapper;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.db.QryPublicQueryVO;
import com.samchat.common.beans.manual.db.QryUserInfoVO;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.db.FollowDbEnum;
import com.samchat.common.exceptions.SysException;
import com.samchat.common.utils.CommonUtil;
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

	public void insertFollow(long userId, long userIdPros, Timestamp sysdate) {
		TOaFollow tf = new TOaFollow();
		tf.setUser_id(userId);
		tf.setUser_id_pro(userIdPros);
		tf.setBlock_tag(FollowDbEnum.Block.UNBLOCK.val());
		tf.setFavourite_tag(Constant.OA_UNFAVOURITE);
		tf.setState(Constant.STATE_IN_USE);
		tf.setState_date(sysdate);
		oaFollowMapper.insert(tf);
	}

	public void deleteFollow(long userId, long userIdPros) {
		TOaFollowExample fe = new TOaFollowExample();
		fe.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(userIdPros)
				.andStateEqualTo(Constant.STATE_IN_USE);
		oaFollowMapper.deleteByExample(fe);
	}

	public void updateBlock(long userId, long userIdPros, byte block, Timestamp sysdate) {
		TOaFollowExample fe = new TOaFollowExample();
		fe.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(userIdPros)
				.andStateEqualTo(Constant.STATE_IN_USE);

		TOaFollow record = new TOaFollow();
		record.setBlock_tag(block);
		record.setState_date(sysdate);

		oaFollowMapper.updateByExampleSelective(record, fe);
	}

	public void updateFavourite(long userId, long userIdPros, byte favourite, Timestamp sysdate) {
		TOaFollowExample fe = new TOaFollowExample();
		fe.createCriteria().andUser_idEqualTo(userId).andUser_id_proEqualTo(userIdPros)
				.andStateEqualTo(Constant.STATE_IN_USE);

		TOaFollow record = new TOaFollow();
		record.setFavourite_tag(favourite);
		record.setState_date(sysdate);

		oaFollowMapper.updateByExampleSelective(record, fe);
	}

	public List<QryFollowVO> queryFollowList(long userId) {
		Map param = new HashMap();
		param.put("user_id", new Long(userId));
		return executeSqlList("queryFollowList", param);
	}

	public List<QryPublicQueryVO> queryPublicList(String key, long count) {

		Map<String, Object> p = new HashMap<String, Object>();
		p.put("key", key);
		
		int ps = getQueryPublicListPageSize();
		if(count < ps && count > 0){
			return new ArrayList<QryPublicQueryVO>();
		}
		int curpage = ((int)count / ps) + 1;
		PageInfo pi = executePageSql("queryPublicList", p, curpage, ps);
		return pi.getList();
	}
	
	private int getQueryPublicListPageSize(){
		int ps = Integer.parseInt(CommonUtil.getSysConfigStr("queryPublicList_page_size"));
		if(ps <= 0){
			throw new SysException("queryPageSize <= 0, error ");
		}
		return ps;
	}

	public List<TOaFollow> queryFollowListByAdserId(long userId) {
		TOaFollowExample ttx = new TOaFollowExample();
		ttx.createCriteria().andUser_id_proEqualTo(userId).andBlock_tagEqualTo(FollowDbEnum.Block.UNBLOCK.val())
				.andStateEqualTo(Constant.STATE_IN_USE);
		return oaFollowMapper.selectByExample(ttx);

	}
	
	public Timestamp queryFollowListStateDate(long userId) {
		Map param = new HashMap();
		param.put("user_id", new Long(userId));
		return (Timestamp)executeSqlOne("queryFollowListStateDate", param);
	}
}
