package com.samchat.common.beans.auto.json.appserver.profile;

public class AppkeyGet_res{

	private Long ret ;
	private String appi = "";
	private String appk = "";
	private String apps = "";

	public Long getRet () {
		return ret ;
	}

	public void setRet (Long ret ) {
		this.ret  = ret ;
	}

	public String getAppi() {
		return appi;
	}

	public void setAppi(String appi) {
		this.appi = (appi == null? "" : appi.trim());
	}

	public String getAppk() {
		return appk;
	}

	public void setAppk(String appk) {
		this.appk = (appk == null? "" : appk.trim());
	}

	public String getApps() {
		return apps;
	}

	public void setApps(String apps) {
		this.apps = (apps == null? "" : apps.trim());
	}

}