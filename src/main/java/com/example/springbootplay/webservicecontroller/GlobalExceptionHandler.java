package com.example.springbootplay.webservicecontroller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Tony Ng on 6/10/2018.
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
	private Logger LOG = LogManager.getLogger(this.getClass());

	@ExceptionHandler(Exception.class)
	@ResponseBody
	ResponseEntity<CustomizeErrorResponse> handleException(Exception e)
	{
		LOG.error(e.getMessage(), e);

		CustomizeErrorResponse response = new CustomizeErrorResponse();
		response.setErrorCode("500");
		response.setMessage("something wrong");

		ResponseEntity<CustomizeErrorResponse> customizeErrorResponseResponseEntity = new ResponseEntity<>(response,
				HttpStatus.INTERNAL_SERVER_ERROR);
		return customizeErrorResponseResponseEntity;
	}

	private class CustomizeErrorResponse
	{
		private String errorCode;
		private String message;

		public String getErrorCode()
		{
			return errorCode;
		}

		public void setErrorCode(String errorCode)
		{
			this.errorCode = errorCode;
		}

		public String getMessage()
		{
			return message;
		}

		public void setMessage(String message)
		{
			this.message = message;
		}
	}
}
