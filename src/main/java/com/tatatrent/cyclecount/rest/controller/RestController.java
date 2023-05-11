package com.tatatrent.cyclecount.rest.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tatatrent.cyclecount.rest.dto.Message;
import com.tatatrent.cyclecount.rest.dto.RequestDto;
import com.tatatrent.cyclecount.rest.service.CycleCountService;
import com.tatatrent.cyclecount.utils.AppConstants;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = AppConstants.API_VERSION)
public class RestController {
	
	@Autowired
	private CycleCountService service;
	
	final static Logger logger = LoggerFactory.getLogger(RestController.class);
	
	@RequestMapping(value = "/message")
	public ResponseEntity<String> getMessage() {
		logger.info("Inside RestController.getMessage()");
		return new ResponseEntity<String>("Hii.. response from api.", HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/process_count", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
	public ResponseEntity<Message> processCount(@Valid @RequestBody RequestDto request) throws SQLException {
		logger.info("Inside RestController.processCount() with SKU: " + request.getSku());
		return service.storeCycleCount(request) > 0 ? new ResponseEntity<Message>(new Message(HttpStatus.CREATED.value(), "Record inserted.", request.getLocId()), HttpStatus.CREATED) : new ResponseEntity<Message>(new Message(), HttpStatus.BAD_REQUEST);
	}


}
