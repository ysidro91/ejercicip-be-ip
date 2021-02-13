package com.exercise.ya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.ya.dto.IpInfoRequest;
import com.exercise.ya.dto.IpInfoResponse;
import com.exercise.ya.dto.SummaryResponse;
import com.exercise.ya.service.IpService;
import com.exercise.ya.service.SummaryService;

@RestController
@RequestMapping("/be")
public class IpController {
	
	@Autowired
	private IpService ipService;
	
	@Autowired
	private SummaryService summaryService;
	
	@PostMapping("/trace")
	public IpInfoResponse getTrace(@RequestBody IpInfoRequest request) {
		IpInfoResponse response = ipService.getTrace(request);
		return response;
	}
	
	@GetMapping("/stats")
	public SummaryResponse getStats() {
		return summaryService.getStats();
	}

}
