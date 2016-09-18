package com.samchat.service.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_req;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;

public interface IAdvertisementSrvs extends IBaseSrvs{
	
	public void saveAdvertisementContent(long adsId, long userIdPro, byte type, String content, String thumb,
			Timestamp recvdate);

	public void saveAdvertisementSendLog(long adsId, long userId, Timestamp senddate, byte state, String clientId, String remark);

	public void updateAdvertisementNotinuse(List<AdvertisementDelete_req.Advertisements> ads, long userId)
			throws Exception;

	public AdvertisementSqs advertisementSend(AdvertisementWrite_req req, TokenRds token, long adsId)
			throws Exception;
}
