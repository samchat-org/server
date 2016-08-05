package com.skyworld.action;

import javax.servlet.http.HttpServlet;

import com.skyworld.common.AppException;

public class ToolAction extends HttpServlet {

	protected String sysErrorRet(int errorCode) {
		return "{ret:" + errorCode + "}";
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
}
