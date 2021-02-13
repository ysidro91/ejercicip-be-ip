package com.exercise.ya.dto.http;

import java.util.List;

public class CountryResponse {
	
	private String name;
	private String nativeName;
	private String alpha2Code;
	private List<String> timezones;
	private List<LanguageInfo> languages;
	private List<CurrencyInfo> currencies;
	private List<Double> latlng;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}

	public String getAlpha2Code() {
		return alpha2Code;
	}
	
	public void setAlpha2Code(String alpha2Code) {
		this.alpha2Code = alpha2Code;
	}

	public List<String> getTimezones() {
		return timezones;
	}

	public void setTimezones(List<String> timezones) {
		this.timezones = timezones;
	}

	public List<LanguageInfo> getLanguages() {
		return languages;
	}

	public void setLanguages(List<LanguageInfo> languages) {
		this.languages = languages;
	}

	public List<CurrencyInfo> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<CurrencyInfo> currencies) {
		this.currencies = currencies;
	}

	public List<Double> getLatlng() {
		return latlng;
	}

	public void setLatlng(List<Double> latlng) {
		this.latlng = latlng;
	}

	@Override
	public String toString() {
		return "{name:" + name + ", nativeName:" + nativeName + ", alpha2Code:" + alpha2Code
				+ ", timezones:" + timezones + ", languages:" + languages + ", currencies:" + currencies + ", latlng:"
				+ latlng + "}";
	}

}
