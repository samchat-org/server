package com.samchat.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementAdvertisements;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_req;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.utils.SqsUtil;
import com.samchat.dao.db.interfaces.IAdvertisementDbDao;
import com.samchat.service.interfaces.IAdvertisementSrvs;

@Service
public class AdvertisementSrvs implements IAdvertisementSrvs {

	@Autowired
	private IAdvertisementDbDao advertisementDbDao;

	public TAdvertisementAdvertisements saveAdvertisement(long userId, byte type, String content, long adsId,
			Timestamp sysdate) {
		return advertisementDbDao.saveAdvertisement(adsId, sysdate, userId, type, content);
	}

	public void updateAdvertisementNotinuse(List<AdvertisementDelete_req.Advertisements> ads, long userId)
			throws Exception {
		for (AdvertisementDelete_req.Advertisements ad : ads) {
			advertisementDbDao.updateAdvertisementNotInuse(ad.getAdv_id(), ad.getPublish_timestamp(), userId);
		}
	}

	public AdvertisementSqs advertisementSend(AdvertisementWrite_req req, TokenRds token, long adsId, Timestamp sysdate)
			throws Exception {

		AdvertisementSqs ads = new AdvertisementSqs();
		AdvertisementWrite_req.Body body = req.getBody();
		ads.setAds_id(adsId);
		ads.setType(body.getType());
		ads.setUser_id(token.getUserId());
		ads.setContent(body.getContent());
		ads.setTime(sysdate.getTime());
		SqsUtil.pushMessage(ads, Constant.SYS_PARAM_KEY.SQS_ADVERTISEMENT);

		return ads;
	}

}
