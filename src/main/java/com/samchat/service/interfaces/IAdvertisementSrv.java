package com.samchat.service.interfaces;

import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementAdvertisements;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_req;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;

public interface IAdvertisementSrv {

	public TAdvertisementAdvertisements saveAdvertisement(long userId, byte type, String content);
	
	public void updateAdvertisementNotinuse(List<AdvertisementDelete_req.Advertisements> ads, long userId) throws Exception;
 
	public AdvertisementSqs advertisementSend(AdvertisementWrite_req req, TokenRds token) throws Exception;
}
