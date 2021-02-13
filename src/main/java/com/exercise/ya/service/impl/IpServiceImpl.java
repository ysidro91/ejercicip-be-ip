package com.exercise.ya.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exercise.ya.dao.RegistryDao;
import com.exercise.ya.dto.IpInfoRequest;
import com.exercise.ya.dto.IpInfoResponse;
import com.exercise.ya.dto.http.CountryResponse;
import com.exercise.ya.dto.http.CurrencyResponse;
import com.exercise.ya.dto.http.IpResponse;
import com.exercise.ya.entity.Registry;
import com.exercise.ya.exception.CallRestException;
import com.exercise.ya.exception.InvalidIpException;
import com.exercise.ya.service.CountryService;
import com.exercise.ya.service.CurrencyService;
import com.exercise.ya.service.IpService;
import com.exercise.ya.util.DtoUtils;
import com.exercise.ya.util.IpUtils;

@Service
public class IpServiceImpl implements IpService {
	
	private static final Logger LOGGER = LogManager.getLogger(IpServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private RegistryDao registryDao;
	
	@Value("${ip.url}")
	private String ipUrl;
	
	@Override
	public IpInfoResponse getTrace(IpInfoRequest request) {
		if (request.getIp() == null || !IpUtils.validate(request.getIp())) {
			throw new InvalidIpException("Invalid IP");
		}
		
		String ipUrlFinal = new StringBuilder(ipUrl).append(request.getIp()).toString();
		LOGGER.info("Request: {}", ipUrlFinal);
		
		IpResponse result = new IpResponse();
		
		try {
			result = restTemplate.getForObject(ipUrlFinal, IpResponse.class);
			LOGGER.info("Ip Service response: {}", result);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new CallRestException("Error calling ip service");
		}
		
		CountryResponse country = countryService.getByName(result.getCountryName());
		
		CurrencyResponse currency = currencyService.getLatest(country.getCurrencies().get(0).getCode());
		
		IpInfoResponse response = DtoUtils.buildIpResponse(request.getIp(), country, currency);
		
		Optional<Registry> queryResult = registryDao.findByCountryName(result.getCountryName());
		if (queryResult.isPresent()) {
			int count = queryResult.get().getCount() + 1;
			queryResult.get().setCount(count);
			registryDao.save(queryResult.get());
			LOGGER.info("Updated: {}", result.getCountryName());
		} else {
			LOGGER.info("Save new country: {}", result.getCountryName());
			Registry registry = new Registry();
			
			registry.setCount(1);
			registry.setCountryName(result.getCountryName());
			registry.setCreateAt(new Date());
			registry.setDistance(
					Double.valueOf(response.getEstimatedDistance().replace("kms", ""))
					);
			
			registryDao.save(registry);
		}
		
		return response;
	}

}
