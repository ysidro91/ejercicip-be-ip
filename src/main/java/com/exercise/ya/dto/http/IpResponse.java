package com.exercise.ya.dto.http;

public class IpResponse {
	
	private String countryCode;
	private String countryCode3;
	private String countryName;
	private String countryEmoji;
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryCode3() {
		return countryCode3;
	}
	public void setCountryCode3(String countryCode3) {
		this.countryCode3 = countryCode3;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryEmoji() {
		return countryEmoji;
	}
	public void setCountryEmoji(String countryEmoji) {
		this.countryEmoji = countryEmoji;
	}
	@Override
	public String toString() {
		return "{countryCode:" + countryCode + ", countryCode3:" + countryCode3 + ", countryName:"
				+ countryName + ", countryEmoji:" + countryEmoji + "}";
	}
	
}
