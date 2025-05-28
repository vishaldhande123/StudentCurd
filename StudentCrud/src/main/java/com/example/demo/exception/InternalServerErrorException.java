package com.example.demo.exception;

public class InternalServerErrorException extends CustomException {
	 public InternalServerErrorException(String message) {
	        super("500", message);
	    }
}
