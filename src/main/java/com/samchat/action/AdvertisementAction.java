package com.samchat.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementDelete_res;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_req;
import com.samchat.common.beans.auto.json.appserver.advertisement.AdvertisementWrite_res;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.ICommonSrvm;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class AdvertisementAction extends BaseAction {

	private static Logger log = Logger.getLogger(AdvertisementAction.class);
	
	@Autowired
	private IUsersSrvs usersSrv;

	@Autowired
	private IAdvertisementSrvs advertisementSrv;

	@Autowired
	private ICommonSrvs commonSrv;
	
	@Autowired
	private ICommonSrvm commonSrvm;

	public AdvertisementWrite_res advertisementWrite(AdvertisementWrite_req req, TokenMappingRds token) throws Exception {

		long qstId = commonSrvm.querySeqId(Constant.SEQUENCE.S_ADVERTISEMENT);
		log.info("qst_id:" + qstId);
		AdvertisementSqs ads = advertisementSrv.advertisementSend(req, token, qstId);
		AdvertisementWrite_res res = new AdvertisementWrite_res();
		res.setAdv_id(ads.getAds_id());
		res.setContent_thumb(ads.getContent_thumb());
		res.setPublish_timestamp(ads.getTime());
		return res;
	}

	public void advertisementWriteValidate(AdvertisementWrite_req req, TokenMappingRds token) {
		TUserUsers user = usersSrv.queryUser(token.getUserId());
		if (user.getUser_type() != Constant.USER_TYPE_SERVICES) {
			throw new AppException(ResCodeAppEnum.USER_PROS_NOT_EXIST.getCode());
		}
	}

	public AdvertisementDelete_res advertisementDelete(AdvertisementDelete_req req, TokenMappingRds token) throws Exception {
		advertisementSrv.updateAdvertisementNotinuse(req.getBody().getAdvertisements(), token.getUserId());
		return new AdvertisementDelete_res();
	}

	public void advertisementDeleteValidate(AdvertisementDelete_req req, TokenMappingRds token) {

	}

}
