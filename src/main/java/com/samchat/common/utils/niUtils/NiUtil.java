package com.samchat.common.utils.niUtils;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samchat.common.beans.auto.json.ni.id.Create_req;
import com.samchat.common.beans.auto.json.ni.id.Create_res;
import com.samchat.common.beans.auto.json.ni.id.Update_req;
import com.samchat.common.beans.auto.json.ni.id.Update_res;
import com.samchat.common.beans.auto.json.ni.msg.SendAttachMsgFieldOption_req;
import com.samchat.common.beans.auto.json.ni.msg.SendAttachMsg_req;
import com.samchat.common.beans.auto.json.ni.msg.SendAttachMsg_res;
import com.samchat.common.beans.auto.json.ni.msg.SendBatchMsgFieldOption_req;
import com.samchat.common.beans.auto.json.ni.msg.SendBatchMsg_req;
import com.samchat.common.beans.auto.json.ni.msg.SendBatchMsg_res;
import com.samchat.common.beans.auto.json.ni.msg.SendMsgFieldOption_req;
import com.samchat.common.beans.auto.json.ni.msg.SendMsg_req;
import com.samchat.common.beans.auto.json.ni.msg.SendMsg_res;
import com.samchat.common.utils.ThreadLocalUtil;

public class NiUtil {

	private static Log log = LogFactory.getLog(NiUtil.class);

	private static final String CREATE_ACTION = "https://api.netease.im/nimserver/user/create.action";

	private static final String UPDATE_ACTION = "https://api.netease.im/nimserver/user/update.action";

	private static final String SEND_MESSAGE = "https://api.netease.im/nimserver/msg/sendMsg.action";
	
	private static final String SEND_BATCH_MESSAGE = "https://api.netease.im/nimserver/msg/sendBatchMsg.action";
	
	private static final String SEND_ATTACH_MESSAGE = "https://api.netease.im/nimserver/msg/sendAttachMsg.action";

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
	
	public static void sendAttachMsg(SendAttachMsg_req msg, Timestamp cur) throws Exception{
		log.info("send sendAttachMsg:" + msg.getAttach());
		ObjectMapper om = ThreadLocalUtil.getAppObjectMapper();
		
		SendAttachMsgFieldOption_req s = new SendAttachMsgFieldOption_req();
		s.setBadge(true);
		s.setNeedPushNick(false);
		s.setRoute(false);

		msg.setOption(om.writeValueAsString(s));
		msg.setSave("2");
		msg.setMsgtype("0");
		
		String body = NiPostClient.post(SEND_ATTACH_MESSAGE, new BeanMap(msg), cur);
		SendAttachMsg_res res= om.readValue(body, SendAttachMsg_res.class);
		if (CODE_SUCCESS != res.getCode()) {
			throw new Exception("sendAttachMsg , ni res code:" + res.getCode());
		}
	}
	
	public static void sendBatchMessage(SendBatchMsg_req batchMessage, Timestamp cur) throws Exception {
		log.info("send batchMessage:" + batchMessage.getBody());
		ObjectMapper om = ThreadLocalUtil.getAppObjectMapper();
		
		SendBatchMsgFieldOption_req s = new SendBatchMsgFieldOption_req();
		s.setRoam(false);        
		s.setHistory(true);     
		s.setSendersync(true);  
		s.setPush(true);        
		s.setRoute(true);       
		s.setBadge(true);       
		s.setNeedPushNick(true);

		String option =om.writeValueAsString(s);
		batchMessage.setOption(option);
		
		String body = NiPostClient.post(SEND_BATCH_MESSAGE, new BeanMap(batchMessage), cur);
		SendBatchMsg_res res = om.readValue(body, SendBatchMsg_res.class);
		if (CODE_SUCCESS != res.getCode()) {
			throw new Exception("sendBatchMessage , ni res code:" + res.getCode());
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
