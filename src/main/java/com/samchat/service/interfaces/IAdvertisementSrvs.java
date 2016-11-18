package com.samchat.service.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContent;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_req;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.enums.db.AdsDbEnum;

public interface IAdvertisementSrvs extends IBaseSrvs {

	public void saveAdvertisementContent(long adsId, long userIdPro, byte type, String content, String thumb,
			Timestamp recvdate, int shardingFlag);

	public void saveAdvertisementSendLog(long adsId, long userId, Timestamp senddate, byte state, String clientId,
			String remark, int shardingFlag);

	public void updateAdvertisementSendLog(long logId, Timestamp senddate, byte state, String clientId, String remark,
			int shardingFlag, int sendcount);

	public void updateAdvertisementNotinuse(List<AdvertisementDelete_req.Advertisements> ads, long userId)
			throws Exception;

	public AdvertisementSqs saveAndSendAdvertisement_master(AdvertisementWrite_req req, long userIdPro, long adsId)
			throws Exception;

	public TAdvertisementContent queryAdvertisementCotentById(long adsId, int shardingFlag) throws Exception;

	public void saveAdvertisementSendLogList_master(long adsId, List<QryFollowVO> qfvlst,
			AdsDbEnum.SendLogState state, int shardingFlag, int pagination);
	
	public boolean updateAdvertisementRecallFlag(long adsId, int shardingFlag);
	
	public boolean updateAdvertisementSendingState(long adsId, int shardingFlag);
	
	public void updateAdvertisementState(long adsId, byte state, int shardingFlag);
}
