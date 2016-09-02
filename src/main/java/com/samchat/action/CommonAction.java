package com.samchat.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.json.appserver.common.SendInviteMsg_req;
import com.samchat.common.beans.auto.json.appserver.common.SendInviteMsg_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.TwilioUtil;

public class CommonAction extends BaseAction {

	private static Logger log = Logger.getLogger(CommonAction.class);

	public SendInviteMsg_res sendInviteMsg(SendInviteMsg_req req, TokenRds token) throws Exception {

		String countrycode = token.getCountryCode();
		String cellphone = token.getCellPhone();

		String smstpl = CommonUtil.getSysConfigStr(Constant.SYS_PARAM_KEY.TWILIO_SEND_INVITE_SMS_TEMPLETE);
		String smsContent = smstpl.replaceAll(Constant.TWILLO_INVITE_USER,
				CommonUtil.getE164PhoneNo(countrycode, cellphone));

		List<SendInviteMsg_req.Phones> lst = req.getBody().getPhones();
		for (SendInviteMsg_req.Phones phone : lst) {
			TwilioUtil.sendSms(countrycode, phone.getCellphone(), smsContent);
		}
		return new SendInviteMsg_res();
	}

	public void sendInviteMsgValidate(SendInviteMsg_req req, TokenRds token) {
 	}
}
