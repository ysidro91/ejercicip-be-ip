package com.exercise.ya.service.impl;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exercise.ya.dto.http.CountryResponse;
import com.exercise.ya.exception.CallRestException;
import com.exercise.ya.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	
	private static final Logger LOGGER = LogManager.getLogger(CountryServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${country.url}")
	private String countryUrl;

	@Override
	public CountryResponse getByName(String countryName) {
		String countryUrlFinal = new StringBuilder(countryUrl).append(countryName).append("?fields=alpha2Code;timezones;languages;currencies;name;nativeName;latlng").toString();
		LOGGER.info("Request: {}", countryUrlFinal);
		
		CountryResponse[] result;
		
		try {
			result = restTemplate.getForObject(countryUrlFinal, CountryResponse[].class);
			LOGGER.info("Country Service response: {}", result);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new CallRestException("Error calling country service");
		}
		
		return Arrays.stream(result)
				.filter(country -> countryName.equalsIgnoreCase(country.getName()) || countryName.equalsIgnoreCase(country.getNativeName()))
				.findFirst().get();
	}

}
