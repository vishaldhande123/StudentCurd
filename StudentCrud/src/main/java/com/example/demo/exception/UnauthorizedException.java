package com.example.demo.exception;

import com.example.demo.common.MessageConstants;

public class UnauthorizedException extends CustomException {
	public UnauthorizedException(String message) {
        super(MessageConstants.NOT_AUTHORIZED, message);
    }
}
