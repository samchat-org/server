package com.samchat.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class TwilioUtil {

	public static void sendSms(String to, String from, String content) throws Exception {
		
		String account = CommonUtil.getSysConfigStr("twilio_account");
		String authToken = CommonUtil.getSysConfigStr("twilio_auth_token");

		TwilioRestClient client = new TwilioRestClient(account, authToken);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Body", content));
		params.add(new BasicNameValuePair("To", to));
		params.add(new BasicNameValuePair("From", from));

		MessageFactory messageFactory = client.getAccount().getMessageFactory();
		messageFactory.create(params);
	}

	public static void main(String args[]) {
	}
}
