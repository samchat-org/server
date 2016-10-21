package com.samchat.common.utils.niUtils;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.samchat.common.beans.auto.json.ni.id.Create_req;
import com.samchat.common.beans.auto.json.ni.id.Create_res;
import com.samchat.common.beans.auto.json.ni.id.Update_req;
import com.samchat.common.beans.auto.json.ni.id.Update_res;
import com.samchat.common.beans.auto.json.ni.msg.SendMsgFieldOption_req;
import com.samchat.common.beans.auto.json.ni.msg.SendMsg_req;
import com.samchat.common.beans.auto.json.ni.msg.SendMsg_res;

public class NiUtil {

	private static Log log = LogFactory.getLog(NiUtil.class);

	private static final String CREATE_ACTION = "https://api.netease.im/nimserver/user/create.action";

	private static final String UPDATE_ACTION = "https://api.netease.im/nimserver/user/update.action";

	private static final String SEND_MESSAGE = "https://api.netease.im/nimserver/msg/sendMsg.action";

	private static final long CODE_SUCCESS = 200;

	public static void createAction(Create_req register, Timestamp cur) throws Exception {
		log.error("register param : " + register);

		String body = NiPostClient.post(CREATE_ACTION, new BeanMap(register), cur);
		ObjectMapper om = new ObjectMapper();
		Create_res res = om.readValue(body, Create_res.class);
		if (CODE_SUCCESS != res.getCode()) {
			throw new Exception("createAction, ni res code:" + res.getCode());
		}
	}

	public static void updateTokenAction(Update_req token, Timestamp cur) throws Exception {
		log.info("update token param : " + token);

		String body = NiPostClient.post(UPDATE_ACTION, new BeanMap(token), cur);
		ObjectMapper om = new ObjectMapper();
		Update_res res = om.readValue(body, Update_res.class);
		if (CODE_SUCCESS != res.getCode()) {
			throw new Exception("updateTokenAction , ni res code:" + res.getCode());
		}
	}
	
	public static void sendMessage(SendMsg_req message, Timestamp cur) throws Exception {
		log.info("send message  : " + message);

		String body = NiPostClient.post(SEND_MESSAGE, new BeanMap(message), cur);
		ObjectMapper om = new ObjectMapper();
		SendMsg_res res = om.readValue(body, SendMsg_res.class);
		if (CODE_SUCCESS != res.getCode()) {
			throw new Exception("sendMessage , ni res code:" + res.getCode());
		}
	}
	
	public static void main(String args[]) throws Exception{
		
		SendMsg_req req = new SendMsg_req();
		req.setFrom("20000000018");
		req.setOpe("0");
		req.setTo("10000000013");
		req.setType("0");
		req.setBody("{\"msg\":\"hello\"}");
		
		SendMsgFieldOption_req fr = new SendMsgFieldOption_req();
		fr.setRoam(false);
		fr.setHistory(false);
		fr.setSendersync(false);
		fr.setPush(true);
		fr.setRoute(false);
		fr.setBadge(true);
		fr.setNeedPushNick(true);
		
		ObjectMapper om = new ObjectMapper();
		req.setOption(om.writeValueAsString(fr));
		req.setPushcontent("ios推送内容，不超过150字符，option选项中允许推送（push=true），此字段可以指定推送内容");
		req.setPayload(" ios 推送对应的payload,必须是JSON,不能超过2k字符");
		req.setExt(" 开发者扩展字段，长度限制1024字符");
		
		sendMessage(req, new Timestamp(new Date().getTime()));
		
	}
}
