package com.samchat.dao.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.action.ProfileAction;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContent;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContentExample;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLog;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLogExample;
import com.samchat.common.beans.auto.db.mapper.TAdvertisementContentMapper;
import com.samchat.common.beans.auto.db.mapper.TAdvertisementSendLogMapper;
import com.samchat.common.enums.AdsState;
import com.samchat.common.enums.Constant;
import com.samchat.dao.db.interfaces.IAdvertisementDbDao;

@Repository
public class AdvertisementDbDao extends BaseDbDao implements IAdvertisementDbDao {
	
	private static Logger log = Logger.getLogger(AdvertisementDbDao.class);

	@Autowired
	private TAdvertisementSendLogMapper advertisementSendLogMapper;

	@Autowired
	private TAdvertisementContentMapper advertisementContentMapper;

	protected String getNamespace() {
		return "adsSqlMapper";
	}

	public void saveAdvertisementContent(long adsId, long userIdPro, byte type, String content, String thumb,
			Timestamp recvdate, int shardingFlag) {

		TAdvertisementContent ads = new TAdvertisementContent();
		ads.setAds_id(adsId);
		ads.setUser_id_pro(userIdPro);
		ads.setAds_type(type);
		ads.setContent(content);
		ads.setContent_thumb(thumb);
		ads.setCreate_date(recvdate);
		ads.setState_date(recvdate);
		ads.setState(Constant.STATE_IN_USE);
		ads.setSharding_flag(shardingFlag);
		advertisementContentMapper.insert(ads);
	}

	public void saveAdvertisementSendLog(long adsId, long userId, Timestamp senddate, byte state, String clientId,
			String remark, int shardingFlag) {

		TAdvertisementSendLog ads = new TAdvertisementSendLog();
		ads.setAds_id(adsId);
		ads.setUser_id(userId);
		ads.setSend_date(senddate);
		ads.setSend_count(1);
		ads.setState(state);
		ads.setClient_id(clientId);
		ads.setRemark(remark);
		ads.setSharding_flag(shardingFlag);
		advertisementSendLogMapper.insert(ads);
	}

	public void delAdvertisementContent(long adsId, long userId, Timestamp timestamp) {
		TAdvertisementContent ads = new TAdvertisementContent();
		ads.setAds_id(adsId);
		ads.setUser_id_pro(userId);
		ads.setState(Constant.STATE_NOT_IN_USE);
		ads.setCreate_date(timestamp);
		advertisementContentMapper.updateByPrimaryKeySelective(ads);
	}

	public List<TAdvertisementSendLog> queryAdvertisementSendLog(long userId, int shardingFlag) throws Exception {
		
		log.info("userId:" + userId + "--shardingFlag:" + shardingFlag);
		TAdvertisementSendLogExample ase = new TAdvertisementSendLogExample();


		ase.createCriteria().andSharding_flagEqualTo(shardingFlag).andUser_idEqualTo(userId);
		return advertisementSendLogMapper.selectByExample(ase);
	}

	public TAdvertisementContent queryAdvertisementCotentById(long adsId, int shardingFlag) throws Exception {
		TAdvertisementContentExample ace = new TAdvertisementContentExample();
		ace.createCriteria().andAds_idEqualTo(adsId).andSharding_flagEqualTo(shardingFlag);
		List<TAdvertisementContent> list = advertisementContentMapper.selectByExample(ace);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
