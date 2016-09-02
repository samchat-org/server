package com.samchat.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementAdvertisements;
import com.samchat.common.beans.auto.db.mapper.TAdvertisementAdvertisementsMapper;
import com.samchat.dao.db.interfaces.IAdvertisementDbDao;

@Repository
public class AdvertisementDbDao extends BaseDbDao implements IAdvertisementDbDao {
	
	@Autowired
	private TAdvertisementAdvertisementsMapper advertisementsMapper;
	
	protected String getNamespace() {
		return "adsSqlMapper";
	}
	
	public TAdvertisementAdvertisements saveAdvertisement(long userId, byte type, String content ){
		TAdvertisementAdvertisements ads = new TAdvertisementAdvertisements();
		
		ads.setAds_id(this.querySeqId("s_advertisement"));
		ads.setUser_id(userId);
		ads.setType(type);
		ads.setContent(content);
		ads.setCreate_date(querySysdate());
		ads.setState_date(ads.getCreate_date());
		ads.setState(Constant.STATE_IN_USE);
		advertisementsMapper.insert(ads);
		
		return ads;
	}
	
	public void updateAdvertisementNotInuse(long adsId, long timestamp, long userId){
		TAdvertisementAdvertisements ads = new TAdvertisementAdvertisements();
		ads.setAds_id(adsId);
		ads.setUser_id(userId);
		ads.setState(Constant.STATE_NOT_IN_USE);
		advertisementsMapper.updateByPrimaryKeySelective(ads);
	}
}
