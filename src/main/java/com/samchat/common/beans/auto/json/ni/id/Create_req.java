package com.samchat.common.beans.auto.json.ni.id;

public class Create_req{

	private String accid;
	private String name;
	private String props;
	private String icon;
	private String token;

	public String getAccid() {
		return accid;
	}

	public void setAccid(String accid) {
		this.accid = (accid == null? null : accid.trim());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = (name == null? null : name.trim());
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = (props == null? null : props.trim());
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = (icon == null? null : icon.trim());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = (token == null? null : token.trim());
	}

}