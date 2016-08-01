package com.skyworld.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.json.JSONTokener;

public abstract class BaseAction  extends HttpServlet {
	
	Log log = LogFactory.getLog(this.getClass());
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
 	}
	
	public void jsonParse(String data){
		JSONObject root = new JSONObject();
		JSONTokener jsonParser = new JSONTokener(data);
		JSONObject request = (JSONObject) jsonParser.nextValue();
 	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Class clazz = this.getClass();
 		String name = clazz.getSimpleName();
 		Object tpl = getRequestTemplete(name);
 		String reqTpl = name + ".req";
 		String data = req.getParameter("data");
 		String action = "register";
 
 		try {
 			Method method = clazz.getDeclaredMethod(action,new Class[]{String.class});
			method.invoke(this, "123");
		} catch (NoSuchMethodException e) {
 			e.printStackTrace();
		} catch (SecurityException e) {
 			e.printStackTrace();
		} catch (IllegalAccessException e) {
 			e.printStackTrace();
		} catch (IllegalArgumentException e) {
 			e.printStackTrace();
		} catch (InvocationTargetException e) {
 			e.printStackTrace();
		}
  
 	}
	
	private Object getRequestTemplete(String apiName){
		return null;
	}
	
	private boolean validateRequest(String data, Object reqTpl){
		return true;
	}
	
	private Object getResponseTemplete(String apiName){
		return null;
	}
	
	
	
	protected void process(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		Class clazz = this.getClass();
		req.getParameter("data");
	}
}
