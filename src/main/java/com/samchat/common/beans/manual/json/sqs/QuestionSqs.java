package com.samchat.common.beans.manual.json.sqs;

import org.apache.commons.lang.StringUtils;

public class QuestionSqs {
	
	private long question_id;
	private long opt;
	private long user_id;
	private String question = "";
	private String place_id = "";
	private String address = "";
	private String longitude = "";
	private String latitude = "";
	private long time;
	
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(long question_id) {
		this.question_id = question_id;
	} 

	public long getOpt() {
		return opt;
	}

	public void setOpt(long opt) {
		this.opt = opt;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = StringUtils.trimToEmpty(question);
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = StringUtils.trimToEmpty(place_id);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = StringUtils.trimToEmpty(address);
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = StringUtils.trimToEmpty(longitude);
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = StringUtils.trimToEmpty(latitude);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
