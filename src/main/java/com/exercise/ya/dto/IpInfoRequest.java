package com.exercise.ya.dto;

public class IpInfoRequest {
	
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "{ip:" + ip + "}";
	}
	
	

}
