package com.samchat.common.beans.manual.json.redis;

public class TokenRds {
	
	private long userId;
	
	private String cellPhone;
	
	private String countryCode;
	
	private String deviceId;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
 		this.countryCode = countryCode;
	}
	
	public String toString(){
		return "cellPhone:" + cellPhone + "--countryCode:" + countryCode + "--deviceId:" + deviceId;
	}
}
