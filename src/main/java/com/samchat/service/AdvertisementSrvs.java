package com.samchat.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContent;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLog;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_req;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.db.AdsDbEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.S3Util;
import com.samchat.common.utils.ShardingUtil;
import com.samchat.common.utils.SqsUtil;
import com.samchat.dao.db.interfaces.IAdvertisementDbDao;
import com.samchat.service.interfaces.IAdvertisementSrvs;

@Service
public class AdvertisementSrvs extends BaseSrvs implements IAdvertisementSrvs {

	@Autowired
	private IAdvertisementDbDao advertisementDbDao;

	public TAdvertisementContent saveAdvertisementContent(long userIdPro, byte type, String content, String thumb,
			Timestamp recvdate, int shardingFlag) {
		return advertisementDbDao.saveAdvertisementContent(userIdPro, type, content, thumb, recvdate,  shardingFlag);
	}

	public void saveAdvertisementSendLog(long adsId, long userId, Timestamp senddate, byte state, String clientId,
			String remark, int shardingFlag) {
		advertisementDbDao.saveAdvertisementSendLog(adsId, userId, senddate, state, clientId, remark, shardingFlag, 1);
	}
	
	public void saveAdvertisementSendLogList_master(long adsId, List<QryFollowVO> qfvlst, AdsDbEnum.SendLogState state, int shardingFlag, int pagination) {
 		Timestamp sendDate = this.querySysdate();
		for(QryFollowVO qfv : qfvlst){
			advertisementDbDao.saveAdvertisementSendLog(adsId, qfv.getUser_id(), sendDate, state.val(), "", state.name(), shardingFlag, pagination);
		}
 	}
	
	public void updateAdvertisementSendLog(long logId, Timestamp senddate, byte state, String clientId, String remark,
			int shardingFlag, int sendcount) {
		advertisementDbDao.updateAdvertisementSendLog(logId, senddate, state, clientId, remark, shardingFlag, sendcount);
	}

	public void updateAdvertisementNotinuse_master(List<AdvertisementDelete_req.Advertisements> ads, long userId, Timestamp sysdate)
			throws Exception {
 		for (AdvertisementDelete_req.Advertisements ad : ads) {
			advertisementDbDao
					.delAdvertisementContent(ad.getAdv_id(), userId, sysdate);
		}
	}

	public AdvertisementSqs saveAndSendAdvertisement_master(AdvertisementWrite_req req, long userIdPro) throws Exception {

		AdvertisementWrite_req.Body body = req.getBody();		
 		String content = body.getContent();
		long adsType = body.getType();
		String contentThumb = "";
		if (adsType == Constant.ADS_TYPE.PIC) {
			contentThumb = S3Util.getThumbPath(body.getContent());
		}
		Timestamp sysdate = querySysdate();
		int shardingFlag = ShardingUtil.getMonthSharding(sysdate);
 		
		TAdvertisementContent adsct = saveAdvertisementContent(userIdPro, (byte) adsType, content,
				contentThumb, sysdate, shardingFlag);

		AdvertisementSqs ads = new AdvertisementSqs();
		ads.setAds_id(ShardingUtil.getMonthShardingId(shardingFlag, adsct.getAds_id()));
		ads.setType(adsType);
		ads.setUser_id_pro(userIdPro);
		ads.setContent(content);
		ads.setContent_thumb(contentThumb);
		
		ads.setSendType((byte) 2);
		ads.setTime(sysdate.getTime());
		SqsUtil.pushMessage(ads, SysParamCodeDbEnum.SQS_ADVERTISEMENT_URL.getParamCode(), SysParamCodeDbEnum.APP_ADVERTISEMENT_RECALL_MINUTE.getParamCode());

		return ads;
	}


	public TAdvertisementContent queryAdvertisementCotentById(long adsId, int shardingFlag) throws Exception {
		return advertisementDbDao.queryAdvertisementCotentById(adsId, shardingFlag);
	}
	
	public boolean updateAdvertisementRecallFlag(long adsId, int shardingFlag){
		List<Byte> expectState = new ArrayList<Byte>();
		expectState.add(AdsDbEnum.ContentState.CANCEL.val());
		expectState.add(AdsDbEnum.ContentState.WAIT.val());
		return advertisementDbDao.updateAdvertisementState(adsId, AdsDbEnum.ContentState.RECALL.val(), expectState, shardingFlag);
	}
	
	public boolean updateAdvertisementSendingState(long adsId, int shardingFlag){
		List<Byte> expectState = new ArrayList<Byte>();
		expectState.add(AdsDbEnum.ContentState.WAIT.val());
		return advertisementDbDao.updateAdvertisementState(adsId, AdsDbEnum.ContentState.SENDING.val(), expectState, shardingFlag);
	}
	
	public void updateAdvertisementState(long adsId, byte state, int shardingFlag){
		advertisementDbDao.updateAdvertisementState(adsId, state, shardingFlag);
	}
}
