package com.samchat.common.beans.auto.json.appserver.common;

import java.util.ArrayList;
public class SendInviteMsg_req{

	private Header header;
	private Body body;

	public static class Header {
		private String action;
		private String token;

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = (action == null? null : action.trim());
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = (token == null? null : token.trim());
		}
	}

	public static class Body {
		private ArrayList<Phones> phones;
		private String msg;

		public ArrayList<Phones> getPhones() {
			return phones;
		}

		public void setPhones(ArrayList<Phones> phones) {
			this.phones = phones;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = (msg == null? null : msg.trim());
		}
	}

	public static class Phones {
		private String countrycode;
		private String cellphone;

		public String getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(String countrycode) {
			this.countrycode = (countrycode == null? null : countrycode.trim());
		}

		public String getCellphone() {
			return cellphone;
		}

		public void setCellphone(String cellphone) {
			this.cellphone = (cellphone == null? null : cellphone.trim());
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}