package com.samchat.common.beans.auto.json.appserver.user;

import java.util.ArrayList;

import com.samchat.common.utils.CommonUtil;

public class Register_res{

	private long ret;
	private String token = "";
	private User user;

	public static class User {
		private long id;
		private long lastupdate;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getLastupdate() {
			return lastupdate;
		}

		public void setLastupdate(long lastupdate) {
			this.lastupdate = lastupdate;
		}
	}

	public long getRet() {
		return ret;
	}

	public void setRet(long ret) {
		this.ret = ret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = (token == null? "" : token.trim());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public static void main(String[] args) throws Exception{
		Register_res res = new Register_res();
		ArrayList lst = new ArrayList();
		lst.add(1L);
		//methodInvoke(Object obj, String methodStr, Object[] paramObjArr, Class[] paramTypeArr)
		CommonUtil.methodInvoke(res, "setRet", new Object[]{new Long(1)}, new Class[]{long.class});
		
		System.out.print(res.getRet());
	}
}