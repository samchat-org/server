package com.samchat.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.samchat.action.BaseAction;
import com.samchat.common.Constant;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class TwilioUtil {
	
	private static Logger log = Logger.getLogger(TwilioUtil.class);

	public static void sendSms(String countrycode, String cellphone, String content) throws Exception {
		
		String twilloPhoneNo = CommonUtil.getSysConfigStr(Constant.SYS_PARAM_KEY.TWILIO_PHONE_NO);
		String account = CommonUtil.getSysConfigStr("twilio_account");
		String authToken = CommonUtil.getSysConfigStr("twilio_auth_token");
		byte enable = CommonUtil.getSysConfigByte("twilio_enable");
		
		if(enable != Constant.STATE_IN_USE){
			return;
		}
		TwilioRestClient client = new TwilioRestClient(account, authToken);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Body", content));
		params.add(new BasicNameValuePair("To", CommonUtil.getE164PhoneNo(countrycode, cellphone)));
		params.add(new BasicNameValuePair("From", twilloPhoneNo));
		
		log.info(params);
		MessageFactory messageFactory = client.getAccount().getMessageFactory();
		messageFactory.create(params);
	}

	public static void main(String args[]) {
	}
}
