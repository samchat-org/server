package com.samchat.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.samchat.common.enums.Constant;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;

public class TwilioUtil {
	
	private static Logger log = Logger.getLogger(TwilioUtil.class);

	public static void sendSms(String countrycode, String cellphone, String content) throws Exception {
		
		String twilloPhoneNo = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.TWILIO_PHONE_NO.getParamCode());
		String account = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.TWILIO_ACCOUNT.getParamCode());
		String authToken = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.TWILIO_AUTH_TOKEN.getParamCode());
		byte enable = CommonUtil.getSysConfigByte(SysParamCodeDbEnum.TWILIO_ENABLE.getParamCode());
		
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
