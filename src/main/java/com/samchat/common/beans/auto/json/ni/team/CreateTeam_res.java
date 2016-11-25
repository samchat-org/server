package com.samchat.common.beans.auto.json.ni.team;

public class CreateTeam_res{

	private Long code;
	private String tid = "";

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = (tid == null? "" : tid.trim());
	}

}