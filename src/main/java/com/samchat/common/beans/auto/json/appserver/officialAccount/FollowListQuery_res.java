package com.samchat.common.beans.auto.json.appserver.officialAccount;

import java.util.ArrayList;
public class FollowListQuery_res{

	private Long ret;
	private Long count;
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
		private Long id;
		private String username = "";
		private Long lastupdate;
		private Avatar avatar;
		private Sam_pros_info sam_pros_info;
		private Long favourite_tag;
		private Long block_tag;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = (username == null? "" : username.trim());
		}

		public Long getLastupdate() {
			return lastupdate;
		}

		public void setLastupdate(Long lastupdate) {
			this.lastupdate = lastupdate;
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

		public Long getFavourite_tag() {
			return favourite_tag;
		}

		public void setFavourite_tag(Long favourite_tag) {
			this.favourite_tag = favourite_tag;
		}

		public Long getBlock_tag() {
			return block_tag;
		}

		public void setBlock_tag(Long block_tag) {
			this.block_tag = block_tag;
		}
	}

	public Long getRet() {
		return ret;
	}

	public void setRet(Long ret) {
		this.ret = ret;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public ArrayList<Users> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Users> users) {
		this.users = users;
	}

}