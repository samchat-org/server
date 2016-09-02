package com.samchat.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_res;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.service.interfaces.IAdvertisementSrv;

public class AdvertisementAction extends BaseAction {

	private static Logger log = Logger.getLogger(AdvertisementAction.class);

	@Autowired
	private IAdvertisementSrv advertisementSrv;

	public AdvertisementWrite_res advertisementWrite(AdvertisementWrite_req req, TokenRds token) throws Exception {
		AdvertisementSqs ads = advertisementSrv.advertisementSend(req, token);
		AdvertisementWrite_res res = new AdvertisementWrite_res();
		res.setAdv_id(ads.getAds_id());
		res.setPublish_timestamp(ads.getTime());
		return res;
	}

	public void advertisementWriteValidate(AdvertisementWrite_req req, TokenRds token) {

	}

	public AdvertisementDelete_res advertisementDelete(AdvertisementDelete_req req, TokenRds token) throws Exception {
		advertisementSrv.updateAdvertisementNotinuse(req.getBody().getAdvertisements(), token.getUserId());
		return new AdvertisementDelete_res();
	}

	public void advertisementDeleteValidate(AdvertisementDelete_req req, TokenRds token) {

	}

}
