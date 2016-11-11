package com.samchat.dao.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.auto.db.entitybeans.TOaFollowExample;
import com.samchat.common.beans.auto.db.mapper.TOaFollowMapper;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.db.QryPublicQueryVO;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.db.FollowDbEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
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
	
	private int getPageSize(String pagesizekey){
 		int ps = Integer.parseInt(CommonUtil.getSysConfigStr(pagesizekey));
		if(ps <= 0){
			throw new SysException("queryPageSize <= 0, error ");
		}
		return ps;
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
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", new Long(userId));
		return executeSqlList("queryFollowList", param);
	}

	public List<QryPublicQueryVO> queryPublicList(long curUserId, String key, long count) {
		
		if(StringUtils.trimToNull(key) == null){
			return new ArrayList<QryPublicQueryVO>();
		}
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("key", key);
		p.put("cur_user_id", new Long(curUserId));
		
		int ps = getPageSize(SysParamCodeDbEnum.PAGE_SIZE_QUERYPUBLICLIST.getParamCode());
		if(count < ps && count > 0){
			return new ArrayList<QryPublicQueryVO>();
		}
		int curpage = ((int)count / ps) + 1;
		PageInfo pi = executePageSql("queryPublicList", p, curpage, ps);
		return pi.getList();
	}
	
	public List<QryFollowVO> queryFollowListByAdserId(long userIdPro, int page) {
		
		int ps = getPageSize(SysParamCodeDbEnum.PAGE_SIZE_QUERYFOLLOWLISTFORADSDSP.getParamCode());
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("user_id_pro", new Long(userIdPro));
		PageInfo pi = executePageSql("queryFollowListForAdsDsp", p, page, ps);
		
		return pi.getList();

	}
	
	public Timestamp queryFollowListStateDate(long userId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", new Long(userId));
		return (Timestamp)executeSqlOne("queryFollowListStateDate", param);
	}
}
