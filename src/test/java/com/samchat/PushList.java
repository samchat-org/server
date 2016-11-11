package com.samchat;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.samchat.common.utils.Md5Util;

public class PushList {
	// 采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	private static String appId = "e56px9TMay6OJRDrwE21P9";
	private static String appKey = "PUnNqMKGxaAdRoWFDmaTX5";
	private static String masterSecret = "rjOCoCL4VJ9Hq2N8wraE19";

	static String CID1 = "";
	// static String CID2 = "453ffc4fec7bfd43d4705639eacb41d0";
	// 别名推送方式
	// static String Alias1 = "";
	// static String Alias2 = "";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	public static void main(String[] args) throws Exception {
		CID1 = Md5Util.getSign4String("1000027", "");
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		// 通知透传模板
		TransmissionTemplate template = getTemplate();
		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		
		// 配置推送目标
		List targets = new ArrayList();
		Target target1 = new Target();
		target1.setAppId(appId);
		target1.setAlias(CID1);
		// target1.setAlias(Alias1);

		targets.add(target1);
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		System.out.println(ret.getResponse().toString());
	}

	public static TransmissionTemplate getTemplate() {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    template.setTransmissionContent("透传内容");
	    template.setTransmissionType(2);
	    APNPayload payload = new APNPayload();
	    payload.setBadge(1);
	    payload.setContentAvailable(1);
	    payload.setSound("default");
	    payload.setCategory("$由客户端定义");
	    //简单模式APNPayload.SimpleMsg 
	    payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));
	    //字典模式使用下者
	    //payload.setAlertMsg(getDictionaryAlertMsg());
	    template.setAPNInfo(payload);
	    return template;
	}
}
