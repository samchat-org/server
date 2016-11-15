package com.samchat.common.beans.auto.json.appserver.question;

import java.util.ArrayList;
public class QueryPopularRequest_res{

	private Long ret;
	private ArrayList<Popular_request> popular_request;

	public static class Popular_request {
		private String content = "";

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = (content == null? "" : content.trim());
		}
	}

	public Long getRet() {
		return ret;
	}

	public void setRet(Long ret) {
		this.ret = ret;
	}

	public ArrayList<Popular_request> getPopular_request() {
		return popular_request;
	}

	public void setPopular_request(ArrayList<Popular_request> popular_request) {
		this.popular_request = popular_request;
	}

}