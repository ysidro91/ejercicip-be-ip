package com.exercise.ya;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.exercise.ya.dto.http.CountryResponse;
import com.exercise.ya.dto.http.CurrencyResponse;
import com.exercise.ya.dto.http.IpResponse;

@SpringBootTest
public class ExternalServiceTests {
	
	/**
	 * Test if external services works
	 */
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${ip.url}")
	private String ipUrl;
	
	@Value("${country.url}")
	private String countryUrl;
	
	@Value("${currency.access.key}")
	private String accessKey;
	
	@Value("${currency.url}")
	private String currencyUrl;
	
	@Test
	void ipService() {
		String ipUrlFinal = new StringBuilder(ipUrl).append("8.8.8.8").toString();
		ResponseEntity<IpResponse> result = restTemplate.getForEntity(ipUrlFinal, IpResponse.class);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void countryService() {
		String countryUrlFinal = new StringBuilder(countryUrl).append("Venezuela").toString();
		ResponseEntity<CountryResponse[]> result = restTemplate.getForEntity(countryUrlFinal, CountryResponse[].class);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void currencyService() {
		String currencyUrlFinal = new StringBuilder(currencyUrl).append("access_key=")
				.append(accessKey).append("&symbols=").append("ARS").toString();
		ResponseEntity<CurrencyResponse> result = restTemplate.getForEntity(currencyUrlFinal, CurrencyResponse.class);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}

}
