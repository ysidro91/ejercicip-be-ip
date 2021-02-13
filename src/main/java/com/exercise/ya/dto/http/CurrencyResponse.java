package com.exercise.ya.dto.http;

import java.util.Map;

public class CurrencyResponse {
	
	private String base;
	private Map<String, Double> rates;
	
	public String getBase() {
		return base;
	}
	
	public void setBase(String base) {
		this.base = base;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "{base:" + base + ", rates:" + rates + "}";
	}

}
