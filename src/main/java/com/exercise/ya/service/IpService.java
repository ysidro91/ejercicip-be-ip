package com.exercise.ya.service;

import com.exercise.ya.dto.IpInfoRequest;
import com.exercise.ya.dto.IpInfoResponse;

public interface IpService {
	
	public IpInfoResponse getTrace(IpInfoRequest request);

}
