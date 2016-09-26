package com.samchat.common.beans.auto.json.ni.id;

public class Update_req{

	private String accid;
	private String props;
	private String token;

	public String getAccid() {
		return accid;
	}

	public void setAccid(String accid) {
		this.accid = (accid == null? null : accid.trim());
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = (props == null? null : props.trim());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = (token == null? null : token.trim());
	}

}