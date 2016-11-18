package com.samchat.common.beans.auto.json.appserver.contact;

import java.util.ArrayList;
public class ContactListQuery_res{

	private Long ret;
	private State_date state_date;
	private Long count;
	private ArrayList<Users> users;

	public static class State_date {
		private Long last;

		public Long getLast() {
			return last;
		}

		public void setLast(Long last) {
			this.last = last;
		}
	}

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
		private Long type;
		private Avatar avatar;
		private Sam_pros_info sam_pros_info;

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

		public Long getType() {
			return type;
		}

		public void setType(Long type) {
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

	public Long getRet() {
		return ret;
	}

	public void setRet(Long ret) {
		this.ret = ret;
	}

	public State_date getState_date() {
		return state_date;
	}

	public void setState_date(State_date state_date) {
		this.state_date = state_date;
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