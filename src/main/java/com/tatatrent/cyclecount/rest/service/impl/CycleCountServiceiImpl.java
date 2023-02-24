package com.tatatrent.cyclecount.rest.service.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatatrent.cyclecount.jdbc.repository.CycleCountRepository;
import com.tatatrent.cyclecount.rest.dto.RequestDto;
import com.tatatrent.cyclecount.rest.service.CycleCountService;

@Service
public class CycleCountServiceiImpl implements CycleCountService{

	@Autowired
	private CycleCountRepository repository;
	
	final static Logger logger = LoggerFactory.getLogger(CycleCountServiceiImpl.class);
	

	@Override
	public Integer storeCycleCount(RequestDto request) throws SQLException {
		logger.info("Inside CycleCountServiceiImpl.storeCycleCount()");
		if(request.getWhseId() != null) {
			Integer number = repository.saveCycleCount(request);
			logger.info("Returning from CycleCountServiceiImpl.storeCycleCount()");
			return number;
		}
		logger.info("Returning from CycleCountServiceiImpl.storeCycleCount()");
		return 0;
	}

}
