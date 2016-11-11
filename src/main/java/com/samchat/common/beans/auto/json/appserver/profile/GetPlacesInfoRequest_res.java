package com.samchat.common.beans.auto.json.appserver.profile;

import java.util.ArrayList;
public class GetPlacesInfoRequest_res{

	private long ret;
	private long count;
	private ArrayList<Places_info> places_info;

	public static class Places_info {
		private String description = "";
		private String place_id = "";

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = (description == null? "" : description.trim());
		}

		public String getPlace_id() {
			return place_id;
		}

		public void setPlace_id(String place_id) {
			this.place_id = (place_id == null? "" : place_id.trim());
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

	public ArrayList<Places_info> getPlaces_info() {
		return places_info;
	}

	public void setPlaces_info(ArrayList<Places_info> places_info) {
		this.places_info = places_info;
	}

}