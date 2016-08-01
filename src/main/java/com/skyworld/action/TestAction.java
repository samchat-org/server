package com.skyworld.action;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.skyworld.service.interfaces.ITestSrv;

public class TestAction  extends BaseAction {
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ITestSrv testSrv;
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws Exception{
//		Method method = this.getClass().getMethod("register",new Class[]{String.class});
//		method.invoke(this, "123");
		Method method = this.getClass().getDeclaredMethod("register", new Class[]{String.class});
		//testSrv.select();
	}
	
	public void register(String args){
		testSrv.select();
	}
	
	public static void main(String args[]){

	}

}
