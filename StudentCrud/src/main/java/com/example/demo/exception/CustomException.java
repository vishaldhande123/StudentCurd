package com.example.demo.exception;

	public class CustomException extends RuntimeException {
	    private final String statusCode;

	    public CustomException(String statusCode, String message) {
	        super(message);
	        this.statusCode = statusCode;
	    }

	    public String getStatusCode() {
	        return statusCode;
	    }
	}
