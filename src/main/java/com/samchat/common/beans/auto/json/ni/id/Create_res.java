package com.samchat.common.beans.auto.json.ni.id;

public class Create_res{

	private Long code;
	private Info info;

	public static class Info {
		private String token = "";
		private String accid = "";
		private String name = "";

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = (token == null? "" : token.trim());
		}

		public String getAccid() {
			return accid;
		}

		public void setAccid(String accid) {
			this.accid = (accid == null? "" : accid.trim());
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = (name == null? "" : name.trim());
		}
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

}