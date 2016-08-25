package com.samchat.common.beans.manual.db;

public class QryFollowVO {
	
	private long user_id;
	private String user_name;
	private long favourite_tag;
	private long block_tag;
	private String origin;
	private String thumb;
	private String company_name;
	private String service_category;
	private String service_description;

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

	public long getFavourite_tag() {
		return favourite_tag;
	}

	public void setFavourite_tag(long favourite_tag) {
		this.favourite_tag = favourite_tag;
	}

	public long getBlock_tag() {
		return block_tag;
	}

	public void setBlock_tag(long block_tag) {
		this.block_tag = block_tag;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getService_category() {
		return service_category;
	}

	public void setService_category(String service_category) {
		this.service_category = service_category;
	}

	public String getService_description() {
		return service_description;
	}

	public void setService_description(String service_description) {
		this.service_description = service_description;
	}
}
