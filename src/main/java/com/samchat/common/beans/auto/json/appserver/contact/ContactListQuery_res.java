package com.samchat.common.beans.auto.json.appserver.contact;

import java.util.ArrayList;
public class ContactListQuery_res{

	private long ret;
	private long count;
	private ArrayList<Users> users;

	public static class Avatar {
		private String thumb;

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = (thumb == null? "" : thumb.trim());
		}
	}

	public static class Sam_pros_info {
		private String service_category;

		public String getService_category() {
			return service_category;
		}

		public void setService_category(String service_category) {
			this.service_category = (service_category == null? "" : service_category.trim());
		}
	}

	public static class Users {
		private long id;
		private String username;
		private long lastupdate;
		private long type;
		private Avatar avatar;
		private Sam_pros_info sam_pros_info;

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

		public long getLastupdate() {
			return lastupdate;
		}

		public void setLastupdate(long lastupdate) {
			this.lastupdate = lastupdate;
		}

		public long getType() {
			return type;
		}

		public void setType(long type) {
			this.type = type;
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