package com.samchat.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.json.appserver.common.SendInviteMsg_req;
import com.samchat.common.beans.auto.json.appserver.common.SendInviteMsg_res;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.cache.UserInfoFieldRdsEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.TwilioUtil;
import com.samchat.service.interfaces.IUsersSrvs;

public class CommonAction extends BaseAction {

	private static Logger log = Logger.getLogger(CommonAction.class);
	
	@Autowired
	private IUsersSrvs usersSrv;

	public SendInviteMsg_res sendInviteMsg(SendInviteMsg_req req, TokenMappingRds token) throws Exception {

		UserInfoRds userInfo = usersSrv.hgetUserInfoJsonObj(token.getUserId());
		String countrycode = userInfo.getCountry_code();
		String cellphone = userInfo.getPhone_no();

		String smstpl = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.TWILIO_SEND_INVITE_SMS_TEMPLETE.getParamCode());
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
}
