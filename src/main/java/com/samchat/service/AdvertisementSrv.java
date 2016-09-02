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
import com.samchat.service.interfaces.IAdvertisementSrv;

@Service
public class AdvertisementSrv implements IAdvertisementSrv{
	
	@Autowired
	private  IAdvertisementDbDao advertisementDbDao;
	
	public TAdvertisementAdvertisements saveAdvertisement(long userId, byte type, String content ){
		return advertisementDbDao.saveAdvertisement(userId, type, content);
	}
	
	public void updateAdvertisementNotinuse(List<AdvertisementDelete_req.Advertisements> ads, long userId) throws Exception{
		for(AdvertisementDelete_req.Advertisements ad : ads){
			advertisementDbDao.updateAdvertisementNotInuse(ad.getAdv_id(), ad.getPublish_timestamp(), userId);
		}
 	}
	
	public AdvertisementSqs advertisementSend(AdvertisementWrite_req req, TokenRds token) throws Exception {
		
		Timestamp sysdate = advertisementDbDao.querySysdate();
		long adsId = advertisementDbDao.querySeqId(Constant.SEQUENCE.S_ADVERTISEMENT);

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
