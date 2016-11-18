package com.samchat.action;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.json.appserver.common.Recall_req;
import com.samchat.common.beans.auto.json.appserver.common.Recall_res;
import com.samchat.common.beans.auto.json.appserver.common.SendInviteMsg_req;
import com.samchat.common.beans.auto.json.appserver.common.SendInviteMsg_res;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.app.CommonAppEnum;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.enums.db.SysMsgTplDbEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.TwilioUtil;
import com.samchat.service.interfaces.IAdvertisementSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class CommonAction extends BaseAction {

	private static Logger log = Logger.getLogger(CommonAction.class);
	
	@Autowired
	private IUsersSrvs usersSrv;
	
	@Autowired
	private IAdvertisementSrvs advertisementSrvs;

	public SendInviteMsg_res sendInviteMsg(SendInviteMsg_req req, TokenMappingRds token) throws Exception {

		UserInfoRds userInfo = usersSrv.hgetUserInfoJsonObj(token.getUserId());
		String countrycode = userInfo.getCountry_code();
		String cellphone = userInfo.getPhone_no();

		String smstpl = CommonUtil.getSysMsgTpl(SysMsgTplDbEnum.ActionCode.SEND_INVITE_SMS.val());
		String smsContent = smstpl.replaceAll(Constant.TWILLO_INVITE_USER,
				CommonUtil.getE164PhoneNo(countrycode, cellphone));

		List<SendInviteMsg_req.Phones> lst = req.getBody().getPhones();
		for (SendInviteMsg_req.Phones phone : lst) {
			TwilioUtil.sendSms(countrycode, phone.getCellphone(), smsContent);
		}
		return new SendInviteMsg_res();
	}

	public void sendInviteMsgValidate(SendInviteMsg_req req, TokenMappingRds token) {
 	}
	
	public Recall_res recall(Recall_req req, TokenMappingRds token){
		Recall_req.Body body = req.getBody();
		Long type = body.getType();
		String bussinessId = body.getBusiness_id();
		Timestamp publishTime = new Timestamp(body.getPublish_timestamp());
		int shardingFlag = CommonUtil.getMonthSharding(publishTime);
 		
		if(type == CommonAppEnum.RecallType.ADVERTISEMENT.val()){
			boolean ret = advertisementSrvs.updateAdvertisementRecallFlag(Long.parseLong(bussinessId), shardingFlag);
			if(ret){
				return new Recall_res();
			}else{
				throw new AppException(ResCodeAppEnum.RECALL_FAILED.getCode());
			}
		}
		return null;
 	}
	
	public void recallValidate(Recall_req req, TokenMappingRds token){
		
	}
}
