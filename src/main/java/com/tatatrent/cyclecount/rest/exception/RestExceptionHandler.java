package com.tatatrent.cyclecount.rest.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
//	final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
		
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        logger.error("MethodArgumentNotValidException: " +apiError.toString());
        return buildResponseEntity(apiError);
    }
	
	
	@ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        logger.error("ConstraintViolationException: " +apiError.toString());
        return buildResponseEntity(apiError);
    }
	
	
	@ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<Object> handleRecordNotFoundException(
    		RecordNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("RECORD_NOT_FOUND");
        logger.error("RecordNotFoundException: " +apiError.toString());
        return buildResponseEntity(apiError);
    }
	
	 private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	        return new ResponseEntity<Object>(apiError, apiError.getStatus());
	    }
}