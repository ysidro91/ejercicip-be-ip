package com.exercise.ya.dto.http;

public class CurrencyInfo {
	
	private String code;
	private String name;
	private String symbol;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "{code:" + code + ", name:" + name + ", symbol:" + symbol + "}";
	}

}
