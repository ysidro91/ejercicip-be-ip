package com.exercise.ya.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exercise.ya.dto.http.CurrencyResponse;
import com.exercise.ya.exception.CallRestException;
import com.exercise.ya.service.CurrencyService;
import com.exercise.ya.util.Constants;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	
	private static final Logger LOGGER = LogManager.getLogger(CurrencyServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${currency.access.key}")
	private String accessKey;
	
	@Value("${currency.url}")
	private String currencyUrl;

	@Override
	public CurrencyResponse getLatest(String currencyCode) {
		// EUR countries ask currency in dollars
		String symbol = Constants.EURO_CODE.equalsIgnoreCase(currencyCode) ? Constants.DOLLAR_CODE : currencyCode;
		String currencyUrlFinal = new StringBuilder(currencyUrl).append("access_key=")
				.append(accessKey).append("&symbols=").append(symbol).toString();
		LOGGER.info("Request: {}", currencyUrlFinal);
		
		CurrencyResponse result = new CurrencyResponse();
		
		try {
			result = restTemplate.getForObject(currencyUrlFinal, CurrencyResponse.class);
			LOGGER.info("Currency Service response: {}", result);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new CallRestException("Error calling currency service");
		}
		
		return result;
	}

}
