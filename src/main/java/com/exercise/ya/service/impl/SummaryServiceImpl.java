package com.exercise.ya.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exercise.ya.dao.RegistryDao;
import com.exercise.ya.dto.SummaryResponse;
import com.exercise.ya.entity.Registry;
import com.exercise.ya.exception.NoDataException;
import com.exercise.ya.service.SummaryService;
import com.exercise.ya.util.DtoUtils;

@Service
public class SummaryServiceImpl implements SummaryService {
	
	private static final Logger LOGGER = LogManager.getLogger(SummaryServiceImpl.class);
	
	@Autowired
	private RegistryDao registryDao;

	@Override
	public SummaryResponse getStats() {
		List<Registry> records = registryDao.findAll(Sort.by(Sort.Direction.DESC,"distance"));
		if (records.size() == 0) {
			LOGGER.info("No data");
			throw new NoDataException("no data");
		}
		
		Double shorterDistance = records.get(0).getDistance();
		Double greaterDistance = records.get(records.size()-1).getDistance();
		
		Double average = 0d;
		Double count = 0d;
		
		for(Registry registry : records) {
			average += (registry.getDistance() * registry.getCount());
			count += registry.getCount();
		}
		
		average = average/count;
		
		return DtoUtils.buildSummaryResponse(shorterDistance, greaterDistance, average);
	}

}
