package com.skyworld.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.skyworld.beans.auto.db.entitybeans.TUserUsers;
import com.skyworld.beans.auto.json.userTpl.Register_req;
import com.skyworld.beans.auto.json.userTpl.Register_res;
import com.skyworld.service.interfaces.IUsersSrv;

public class UserAction  extends BaseAction {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IUsersSrv usersSrv;
	
	public Register_res registero(Register_req req){
		TUserUsers userUsers = usersSrv.qryUserInfo("", "");
		System.out.print(123456);
		return null;
	}
	
	public static void main(String args[]) throws Exception{
		Class.forName("com.skyworld.beans.auto.json.userTpl.registerCodeRequest_req");
	}

}
