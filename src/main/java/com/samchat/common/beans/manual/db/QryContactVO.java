package com.samchat.common.beans.manual.db;

import java.sql.Timestamp;

public class QryContactVO {

	private long user_id;
	private String user_name;
	private Timestamp lastupdate;
	private long type;
	private String thumb;
	private String service_category;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Timestamp getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getService_category() {
		return service_category;
	}

	public void setService_category(String service_category) {
		this.service_category = service_category;
	}

}
