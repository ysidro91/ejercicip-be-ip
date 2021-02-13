package com.exercise.ya.service;

import com.exercise.ya.dto.http.CurrencyResponse;

public interface CurrencyService {
	
	public CurrencyResponse getLatest(String currencyCode);

}
