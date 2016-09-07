package com.samchat.action;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_res;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.ICommonSrvm;

public class AdvertisementAction extends BaseAction {

	private static Logger log = Logger.getLogger(AdvertisementAction.class);

	@Autowired
	private IAdvertisementSrvs advertisementSrv;
	
	@Autowired
	private ICommonSrvm commonSrvm;

	public AdvertisementWrite_res advertisementWrite(AdvertisementWrite_req req, TokenRds token) throws Exception {
		
		Timestamp sysdate = commonSrvm.querySysdate();
		long qstId = commonSrvm.querySeqId(Constant.SEQUENCE.S_ADVERTISEMENT);
		log.info("qst_id:" + qstId);
		AdvertisementSqs ads = advertisementSrv.advertisementSend(req, token, qstId, sysdate);
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
