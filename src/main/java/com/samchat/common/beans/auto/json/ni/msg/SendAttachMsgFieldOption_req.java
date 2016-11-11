package com.samchat.common.beans.auto.json.ni.msg;

public class SendAttachMsgFieldOption_req{

	private boolean badge;
	private boolean needPushNick;
	private boolean route;

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

	public boolean getRoute() {
		return route;
	}

	public void setRoute(boolean route) {
		this.route = route;
	}

}