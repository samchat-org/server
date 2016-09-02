package com.samchat.common.beans.auto.json.appserver.profile;

public class AppkeyGet_res{

	private long ret ;
	private String appi;
	private String appk;
	private String apps;

	public long getRet () {
		return ret ;
	}

	public void setRet (long ret ) {
		this.ret  = ret ;
	}

	public String getAppi() {
		return appi;
	}

	public void setAppi(String appi) {
		this.appi = (appi == null? null : appi.trim());
	}

	public String getAppk() {
		return appk;
	}

	public void setAppk(String appk) {
		this.appk = (appk == null? null : appk.trim());
	}

	public String getApps() {
		return apps;
	}

	public void setApps(String apps) {
		this.apps = (apps == null? null : apps.trim());
	}

}