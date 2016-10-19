package com.samchat.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContent;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLog;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_req;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.utils.S3Util;
import com.samchat.common.utils.SqsUtil;
import com.samchat.dao.db.interfaces.IAdvertisementDbDao;
import com.samchat.service.interfaces.IAdvertisementSrvs;

@Service
public class AdvertisementSrvs extends BaseSrvs implements IAdvertisementSrvs {

	@Autowired
	private IAdvertisementDbDao advertisementDbDao;

	public void saveAdvertisementContent(long adsId, long userIdPro, byte type, String content, String thumb,
			Timestamp recvdate, int shardingFlag) {
		advertisementDbDao.saveAdvertisementContent(adsId, userIdPro, type, content, thumb, recvdate,  shardingFlag);
	}

	public void saveAdvertisementSendLog(long adsId, long userId, Timestamp senddate, byte state, String clientId,
			String remark, int shardingFlag) {
		advertisementDbDao.saveAdvertisementSendLog(adsId, userId, senddate, state, clientId, remark, shardingFlag);
	}
	
	public void updateAdvertisementSendLog(long logId, Timestamp senddate, byte state, String clientId, String remark,
			int shardingFlag, int sendcount) {
		advertisementDbDao.updateAdvertisementSendLog(logId, senddate, state, clientId, remark, shardingFlag, sendcount);
	}

	public void updateAdvertisementNotinuse(List<AdvertisementDelete_req.Advertisements> ads, long userId)
			throws Exception {
		for (AdvertisementDelete_req.Advertisements ad : ads) {
			advertisementDbDao
					.delAdvertisementContent(ad.getAdv_id(), userId, new Timestamp(ad.getPublish_timestamp()));
		}
	}

	public AdvertisementSqs advertisementSend(AdvertisementWrite_req req, TokenMappingRds token, long adsId) throws Exception {

		AdvertisementSqs ads = new AdvertisementSqs();
		AdvertisementWrite_req.Body body = req.getBody();
		long adsType = body.getType();

		ads.setAds_id(adsId);
		ads.setType(adsType);
		ads.setUser_id_pro(token.getUserId());
		ads.setContent(body.getContent());
		ads.setContent_thumb(body.getContent_thumb());
		if (adsType == Constant.ADS_TYPE.PIC) {
			String thumbpath = S3Util.getThumbPath(body.getContent());
			ads.setContent_thumb(thumbpath);
		}
		Timestamp sysdate = this.querySysdate();
		ads.setSendType((byte) 2);
		ads.setTime(sysdate.getTime());
		SqsUtil.pushMessage(ads, SysParamCodeDbEnum.SQS_ADVERTISEMENT.getParamCode());

		return ads;
	}

	public List<TAdvertisementSendLog> queryAdvertisementSendLog(long userId, int shardingFlag) throws Exception {
		return advertisementDbDao.queryAdvertisementSendLog(userId, shardingFlag);
	}

	public TAdvertisementContent queryAdvertisementCotentById(long adsId, int shardingFlag) throws Exception {
		return advertisementDbDao.queryAdvertisementCotentById(adsId, shardingFlag);
	}
}
