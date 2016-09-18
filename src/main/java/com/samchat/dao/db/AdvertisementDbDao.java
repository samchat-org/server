package com.samchat.dao.db;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContent;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLog;
import com.samchat.common.beans.auto.db.mapper.TAdvertisementContentMapper;
import com.samchat.common.beans.auto.db.mapper.TAdvertisementSendLogMapper;
import com.samchat.common.enums.Constant;
import com.samchat.dao.db.interfaces.IAdvertisementDbDao;

@Repository
public class AdvertisementDbDao extends BaseDbDao implements IAdvertisementDbDao {

	@Autowired
	private TAdvertisementSendLogMapper advertisementSendLogMapper;
	
	@Autowired
	private TAdvertisementContentMapper advertisementContentMapper;

	protected String getNamespace() {
		return "adsSqlMapper";
	}
	
	public void saveAdvertisementContent(long adsId, long userIdPro, byte type,
			String content, String thumb, Timestamp recvdate) {
		
		TAdvertisementContent ads = new TAdvertisementContent();
		ads.setAds_id(adsId);
		ads.setUser_id_pro(userIdPro);
 		ads.setAds_type(type);
		ads.setContent(content);
		ads.setContent_thumb(thumb);
 		ads.setCreate_date(recvdate);
 		ads.setState(Constant.STATE_IN_USE);
 		advertisementContentMapper.insert(ads);
	}
	
	public void saveAdvertisementSendLog(long adsId, long userId, Timestamp senddate, byte state, String clientId, String remark){
		
		TAdvertisementSendLog ads = new TAdvertisementSendLog();
		ads.setAds_id(adsId);
 		ads.setUser_id(userId);
 		ads.setSend_date(senddate);
		ads.setSend_count(1);
		ads.setState(state);
		ads.setClient_id(clientId);
		ads.setRemark(remark);
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
}
