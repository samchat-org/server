package com.samchat.action;

import java.sql.Timestamp;

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

	public AdvertisementWrite_res advertisementWrite(AdvertisementWrite_req req, TokenMappingRds token) throws Exception {

 		long userIdPro = token.getUserId();
		AdvertisementSqs ads = advertisementSrv.saveAndSendAdvertisement_master(req, userIdPro);
		
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
		Timestamp sysdate = commonSrv.querySysdate();
		advertisementSrv.updateAdvertisementNotinuse_master(req.getBody().getAdvertisements(), token.getUserId(), sysdate);
		return new AdvertisementDelete_res();
	}

	public void advertisementDeleteValidate(AdvertisementDelete_req req, TokenMappingRds token) {

	}

}
