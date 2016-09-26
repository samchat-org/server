package com.samchat.common.beans.auto.json.ni.msg;

public class SendMsgFieldOption_req{

	private boolean push;
	private boolean roam;
	private boolean history;
	private boolean sendersync;
	private boolean route;
	private boolean badge;
	private boolean needPushNick;

	public boolean getPush() {
		return push;
	}

	public void setPush(boolean push) {
		this.push = push;
	}

	public boolean getRoam() {
		return roam;
	}

	public void setRoam(boolean roam) {
		this.roam = roam;
	}

	public boolean getHistory() {
		return history;
	}

	public void setHistory(boolean history) {
		this.history = history;
	}

	public boolean getSendersync() {
		return sendersync;
	}

	public void setSendersync(boolean sendersync) {
		this.sendersync = sendersync;
	}

	public boolean getRoute() {
		return route;
	}

	public void setRoute(boolean route) {
		this.route = route;
	}

	public boolean getBadge() {
		return badge;
	}

	public void setBadge(boolean badge) {
		this.badge = badge;
	}

	public boolean getNeedPushNick() {
		return needPushNick;
	}

	public void setNeedPushNick(boolean needPushNick) {
		this.needPushNick = needPushNick;
	}

}