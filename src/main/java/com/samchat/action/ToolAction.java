package com.samchat.action;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.service.interfaces.IUsersSrvs;

public class ToolAction extends HttpServlet {
	
	
	
	@Autowired
	protected IUsersSrvs usersSrv;

	protected String sysErrorRet(int errorCode) {
		return "{\"ret\":" + errorCode + "}";
	}

	protected Class classforName(String classpath, int innerCode) {
		Class tplclazz = null;
		try {
			tplclazz = Class.forName(classpath);
		} catch (ClassNotFoundException e1) {
			throw new AppException(innerCode, "class is not found:" + classpath);
		}
		return tplclazz;
	}
	
	protected TokenMappingRds tokenIdentify(String token) throws Exception{
		TokenMappingRds tokenObj = usersSrv.getTokenObj(token);
		if(tokenObj == null){
			throw new AppException(ResCodeAppEnum.TOKEN_ILLEGAL.getCode());
		}
		return tokenObj;
	}
}
