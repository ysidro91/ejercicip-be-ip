package com.exercise.ya.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import com.exercise.ya.dto.IpInfoResponse;
import com.exercise.ya.dto.SummaryResponse;
import com.exercise.ya.dto.http.CountryResponse;
import com.exercise.ya.dto.http.CurrencyResponse;
import com.exercise.ya.dto.http.LanguageInfo;

public class DtoUtils {
	
	public static IpInfoResponse buildIpResponse(String ip, CountryResponse country, CurrencyResponse currency) {
		IpInfoResponse response = new IpInfoResponse();
		
		response.setIp(ip);
		response.setIso_code(country.getAlpha2Code().toLowerCase());
		
		String countryName = String.format(Constants.TEXT_FORMAT_LIST, country.getNativeName(), country.getName().toLowerCase());
		response.setCountry(countryName);
		
		Date currentDate = new Date();
		response.setDate(DateUtils.formatDate(currentDate));
		
		response.setTimes(buildTimesList(currentDate, country.getTimezones()));
		response.setLanguages(buildLanguagesList(country.getLanguages()));
		
		if (!country.getLatlng().isEmpty()) {
			String distance = String.format(Constants.TEXT_FORMAT_DISTANCE, 
					DistanceUtils.getBuenosAiresDistance(
							country.getLatlng().get(0), country.getLatlng().get(1))
					);
			response.setEstimatedDistance(distance);
		}
		
		response.setCurrency(buildCurrency(country.getCurrencies().get(0).getCode(), currency));
		
		return response;
	}
	
	public static SummaryResponse buildSummaryResponse(Double shorterDistance, Double greaterDistance, Double average) {
		SummaryResponse response = new SummaryResponse();
		
		response.setShorterDistance(String.format(Constants.TEXT_FORMAT_DISTANCE, shorterDistance));
		response.setGreaterDistance(String.format(Constants.TEXT_FORMAT_DISTANCE, greaterDistance));
		response.setAverageTotal(String.format(Constants.TEXT_FORMAT_DISTANCE, average));
		
		return response;
	}
	
	private static List<String> buildTimesList(Date date, List<String> timezones) {
		List<String> times = new ArrayList<>();
		timezones.forEach(
				(timezone) -> times.add(String.format(Constants.TEXT_FORMAT_LIST, DateUtils.changeTimezone(date, timezone), timezone))
		);
		return times;
	}
	
	private static List<String> buildLanguagesList(List<LanguageInfo> languagesInfo) {
		List<String> languages = new ArrayList<>();
		languagesInfo.forEach(
				(language) -> languages.add(String.format(Constants.TEXT_FORMAT_LIST, language.getNativeName(), language.getIso639_1()))
		);
		return languages;
	}
	
	private static String buildCurrency(String countryCurrency, CurrencyResponse currency) {
		Entry<String,Double> entry = currency.getRates().entrySet().iterator().next();
		// In case of EURO, compare with dollar
		Double finalValue = Constants.EURO_CODE.equalsIgnoreCase(countryCurrency) ? entry.getValue() : 1/entry.getValue();
		// If country currency is EURO
		String codeResult = countryCurrency.equalsIgnoreCase(entry.getKey()) ? currency.getBase() : entry.getKey();
		String currencyInfo = String.format(Constants.TEXT_FORMAT_CURRENCY, 
				countryCurrency, countryCurrency, finalValue, codeResult);
		
		return currencyInfo;
	}

}
