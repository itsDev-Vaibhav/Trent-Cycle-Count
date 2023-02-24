package com.tatatrent.cyclecount.rest.service;

import java.sql.SQLException;

import com.tatatrent.cyclecount.rest.dto.RequestDto;


public interface CycleCountService {
	Integer storeCycleCount(RequestDto request) throws SQLException;
}