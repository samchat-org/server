package com.samchat.common.beans.auto.json.ni.msg;

public class SendBatchMsgFieldOption_req{

	private Boolean roam;
	private Boolean history;
	private Boolean sendersync;
	private Boolean push;
	private Boolean route;
	private Boolean badge;
	private Boolean needPushNick;

	public Boolean getRoam() {
		return roam;
	}

	public void setRoam(Boolean roam) {
		this.roam = roam;
	}

	public Boolean getHistory() {
		return history;
	}

	public void setHistory(Boolean history) {
		this.history = history;
	}

	public Boolean getSendersync() {
		return sendersync;
	}

	public void setSendersync(Boolean sendersync) {
		this.sendersync = sendersync;
	}

	public Boolean getPush() {
		return push;
	}

	public void setPush(Boolean push) {
		this.push = push;
	}

	public Boolean getRoute() {
		return route;
	}

	public void setRoute(Boolean route) {
		this.route = route;
	}

	public Boolean getBadge() {
		return badge;
	}

	public void setBadge(Boolean badge) {
		this.badge = badge;
	}

	public Boolean getNeedPushNick() {
		return needPushNick;
	}

	public void setNeedPushNick(Boolean needPushNick) {
		this.needPushNick = needPushNick;
	}

}