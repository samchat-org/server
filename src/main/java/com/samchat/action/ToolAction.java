package com.samchat.action;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
import com.samchat.common.beans.manual.json.redis.TokenRds;
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
	
	protected TokenRds tokenIdentify(String token){
		TokenRds tokenObj = usersSrv.getTokenObj(token);
		if(tokenObj == null){
			throw new AppException(Constant.ERROR.TOKEN_ILLEGAL);
		}
		return tokenObj;
	}
}
