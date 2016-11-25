package com.samchat.dao.db;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContent;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContentExample;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLog;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLogExample;
import com.samchat.common.beans.auto.db.mapper.TAdvertisementContentMapper;
import com.samchat.common.beans.auto.db.mapper.TAdvertisementSendLogMapper;
import com.samchat.common.beans.manual.common.MonthShardingBean;
import com.samchat.common.enums.db.AdsDbEnum;
import com.samchat.common.utils.ShardingUtil;
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

	public TAdvertisementContent saveAdvertisementContent(long userIdPro, byte type, String content, String thumb,
			Timestamp recvdate, int shardingFlag) {

		TAdvertisementContent ads = new TAdvertisementContent();
 		ads.setUser_id_pro(userIdPro);
		ads.setAds_type(type);
		ads.setContent(content);
 		ads.setContent_thumb(thumb);
		ads.setCreate_date(recvdate);
		ads.setState_date(recvdate);
		ads.setState(AdsDbEnum.ContentState.WAIT.val());
		ads.setSharding_flag(shardingFlag);
		advertisementContentMapper.insert(ads);
		
		return ads;
	}

	public TAdvertisementContent queryAdvertisementCotentById(long adsId, int shardingFlag) throws Exception {

		log.info("adsId:" + adsId + "--shardingFlag:" + shardingFlag);
		TAdvertisementContentExample ace = new TAdvertisementContentExample();
		ace.createCriteria().andAds_idEqualTo(adsId).andSharding_flagEqualTo(shardingFlag);
		List<TAdvertisementContent> list = advertisementContentMapper.selectByExample(ace);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void delAdvertisementContent(long adsId, long userId, Timestamp sysdate) {
		MonthShardingBean sharding = ShardingUtil.getMonthSharding(adsId);
		TAdvertisementContent ads = new TAdvertisementContent();
		ads.setAds_id(sharding.getId());
		ads.setUser_id_pro(userId);
		ads.setState(AdsDbEnum.ContentState.CANCEL.val());
		ads.setState_date(sysdate);
 		ads.setSharding_flag(sharding.getShardingFlag());
		advertisementContentMapper.updateByPrimaryKeySelective(ads);
	}

	public void saveAdvertisementSendLog(long adsId, long userId, Timestamp senddate, byte state, String clientId,
			String remark, int shardingFlag, int pagination) {

		TAdvertisementSendLog ads = new TAdvertisementSendLog();
		ads.setAds_id(adsId);
		ads.setUser_id(userId);
		ads.setSend_date(senddate);
		ads.setSend_count(1);
		ads.setState(state);
		ads.setClient_id(clientId);
		ads.setRemark(remark);
		ads.setSharding_flag(shardingFlag);
		ads.setPagination(pagination);
		advertisementSendLogMapper.insert(ads);
	}

	public void updateAdvertisementSendLog(long logId, Timestamp senddate, byte state, String clientId, String remark,
			int shardingFlag, int sendcount) {
		log.info("log_id:" + logId + "--client_id:" + clientId + "--shardingFlag:" + shardingFlag);
		TAdvertisementSendLog ass = new TAdvertisementSendLog();
		if(senddate != null){
			ass.setSend_date(senddate);
		}
		if(sendcount != 0){
			ass.setSend_count(sendcount);
		}
		if(clientId != null){
			ass.setClient_id(clientId);
		}
		ass.setState(state);
		ass.setRemark(remark);
 		
		TAdvertisementSendLogExample ase = new TAdvertisementSendLogExample();
		ase.createCriteria().andLog_idEqualTo(logId).andSharding_flagEqualTo(shardingFlag);
		
		advertisementSendLogMapper.updateByExampleSelective(ass, ase);
	}
	
	public boolean updateAdvertisementState(long adsId, byte destState, List<Byte> expectState, int shardingFlag){
		TAdvertisementContentExample ace = new TAdvertisementContentExample();
		log.info("adsId:" + adsId + "--shardingFlag:" + shardingFlag);
		ace.createCriteria().andAds_idEqualTo(adsId).andStateIn(expectState).andSharding_flagEqualTo(shardingFlag);
		
		TAdvertisementContent tac = new TAdvertisementContent();
		tac.setState(destState);
		int ret = advertisementContentMapper.updateByExampleSelective(tac, ace);
		return ret > 0;
	}
	
	public void updateAdvertisementState(long adsId, byte state, int shardingFlag){
		
		TAdvertisementContentExample ace = new TAdvertisementContentExample();
		log.info("adsId:" + adsId + "--shardingFlag:" + shardingFlag);
		ace.createCriteria().andAds_idEqualTo(adsId).andSharding_flagEqualTo(shardingFlag);
		
		TAdvertisementContent ads = new TAdvertisementContent();
 		ads.setState(state);
 		
 		advertisementContentMapper.updateByExampleSelective(ads, ace);
	}
}
