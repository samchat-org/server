package com.samchat.common.utils.niUtils;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.samchat.common.beans.auto.json.ni.id.Create_res;

public class NiUtil {
	
	private static Log log = LogFactory.getLog(NiUtil.class);

	private static final String CREATE_ACTION = "https://api.netease.im/nimserver/user/create.action";
	
	private static final String UPDATE_ACTION = "https://api.netease.im/nimserver/user/update.action";
	
	private static final String CODE_SUCCESS = "200";
	
	

	public static void createAction(Map<String, String> register, Timestamp cur) throws Exception {
		log.info("register param : " + register);
		
		String body = NiPostClient.post(CREATE_ACTION, register, cur);
		ObjectMapper om = new ObjectMapper();
		Create_res res = om.readValue(body, Create_res.class);
 		if(!CODE_SUCCESS.equals(res.getCode())){
 			throw new Exception("ni res code:" + res.getCode());
 		}
 		
	}
}
