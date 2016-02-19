package com.rick.scaffold.web.api.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Optional;
import com.rick.scaffold.common.Constants;
import com.rick.scaffold.common.utils.ExceptionUtils;
import com.rick.scaffold.exception.APIException;
import com.rick.scaffold.web.bean.generic.FailBean;
import com.rick.scaffold.web.bean.generic.SuccessBean;

public class BaseAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseAPI.class);
	
	protected ResponseEntity<Object> enableCorsRequests(Object entity, int statusCode) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-FRAME-OPTIONS", "DENY");
		return new ResponseEntity<Object>(entity, headers, HttpStatus.valueOf(statusCode));
	}

	protected ResponseEntity<Object> responseSuccess(Object entity) {
		int statusCode = 200;
		SuccessBean sb = new SuccessBean(statusCode, Constants.HTTP_STATUS_OK, entity);
		return enableCorsRequests(sb, statusCode);		
	}	
	
	protected ResponseEntity<Object> responseSuccess(Object entity, int statusCode) {
		SuccessBean sb = new SuccessBean(statusCode, Constants.HTTP_STATUS_OK, entity);
		return enableCorsRequests(sb, statusCode);		
	}
	
	protected ResponseEntity<Object> responseFail(String message) {
		int statusCode = 500;
		String trackId = ExceptionUtils.getTrackID();
		FailBean fb = new FailBean(statusCode, Constants.HTTP_STATUS_ERROR, message, trackId);
		logger.warn("Unexpected result occured. TrackId: {}. Message: {}.", trackId, message);
		return enableCorsRequests(fb, statusCode);		
	}
	
	protected ResponseEntity<Object> responseFail(String message, int statusCode) {
		String trackId = ExceptionUtils.getTrackID();
		FailBean fb = new FailBean(statusCode, Constants.HTTP_STATUS_ERROR, message, trackId);
		logger.warn("Unexpected result occured. TrackId: {}. Message: {}. ", trackId, message);		
		return enableCorsRequests(fb, statusCode);		
	}
	
	@ExceptionHandler(Exception.class)  
    protected @ResponseBody  
    ResponseEntity<Object> exceptionHandler(Exception e) {
		int status;
		FailBean fb;
		String trackId = ExceptionUtils.getTrackID();
		if (e instanceof APIException) {
			APIException e1 = (APIException)e;	
			logger.error("APIException. Error code: {}. TrackId : {}. Error message: {}. ", e1.getErrorCode(), trackId, 
					e1.getMessage(), Optional.fromNullable(e1.getCause()).or(e1));
			fb = new FailBean(e1.getErrorCode(), Constants.HTTP_STATUS_ERROR, e1.getMessage(), trackId);
			status = e1.getErrorCode();
		} else {
			logger.error("APIException. Error code: 500. TrackId : {}. Error message: {}. ", trackId, e.getMessage(), e);
			fb = new FailBean(500, Constants.HTTP_STATUS_ERROR, "出错啦！请联系管理员。", trackId);
			status = 500;
		}
		return enableCorsRequests(fb, status);
    }
}
