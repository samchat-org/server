package com.samchat.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class GetuiUtil {

	private static Logger log = Logger.getLogger(GetuiUtil.class);

	static {
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
	}

	private static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	public static void push(String id, String msg) throws Exception {

		String appId = CommonUtil.getSysConfigStr("getui_app_id");
		String appKey = CommonUtil.getSysConfigStr("getui_app_key");
		String masterSecret = CommonUtil.getSysConfigStr("getui_app_master_secret");
		int offlineTime = CommonUtil.getSysConfigInt("getui_offline_time");
 		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTransmissionContent(msg);
		template.setTransmissionType(2);
		APNPayload payload = new APNPayload();
 		payload.setContentAvailable(1);
 		// 简单模式APNPayload.SimpleMsg
 		
 		payload.setAlertMsg(new APNPayload.SimpleAlertMsg(msg));
		// 字典模式使用下者
		// payload.setAlertMsg(getDictionaryAlertMsg());
		template.setAPNInfo(payload);

		ListMessage message = new ListMessage();
		message.setData(template);
		message.setOffline(true);
 		message.setOfflineExpireTime(offlineTime * 1000 * 3600);
		List<Target> targets = new ArrayList<Target>();
		Target target1 = new Target();
		target1.setAppId(appId);

		target1.setAlias(Md5Util.getSign4String(id));
		targets.add(target1);
 		IGtPush push = new IGtPush(host, appKey, masterSecret);
		
 		String taskId = push.getContentId(message);
		
 		IPushResult ret = push.pushMessageToList(taskId, targets);
		log.info("IPushResult : " + ret.getResponse());
	}

	public static void main(String args[]) throws Exception {
		GetuiUtil.push("1000029", "12312");
	}
}