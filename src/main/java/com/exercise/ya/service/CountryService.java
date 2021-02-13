package com.exercise.ya.service;

import com.exercise.ya.dto.http.CountryResponse;

public interface CountryService {
	
	public CountryResponse getByName(String countryName);

}
