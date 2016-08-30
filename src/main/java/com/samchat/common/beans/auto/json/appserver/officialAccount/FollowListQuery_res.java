package com.samchat.common.beans.auto.json.appserver.officialAccount;

import java.util.ArrayList;
public class FollowListQuery_res{

	private long ret;
	private long count;
	private ArrayList<Users> users;

	public static class Avatar {
		private String thumb = "";

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = (thumb == null? "" : thumb.trim());
		}
	}

	public static class Sam_pros_info {
		private String service_category = "";

		public String getService_category() {
			return service_category;
		}

		public void setService_category(String service_category) {
			this.service_category = (service_category == null? "" : service_category.trim());
		}
	}

	public static class Users {
		private long id;
		private String username = "";
		private Avatar avatar;
		private Sam_pros_info sam_pros_info;
		private long favourite_tag;
		private long block_tag;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = (username == null? "" : username.trim());
		}

		public Avatar getAvatar() {
			return avatar;
		}

		public void setAvatar(Avatar avatar) {
			this.avatar = avatar;
		}

		public Sam_pros_info getSam_pros_info() {
			return sam_pros_info;
		}

		public void setSam_pros_info(Sam_pros_info sam_pros_info) {
			this.sam_pros_info = sam_pros_info;
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
	}

	public long getRet() {
		return ret;
	}

	public void setRet(long ret) {
		this.ret = ret;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public ArrayList<Users> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Users> users) {
		this.users = users;
	}

}