package com.example.demo.exception;

import com.example.demo.common.MessageConstants;

public class BadRequestException extends CustomException {
	public BadRequestException(String message) {
        super(MessageConstants.BAD_REQUEST_STATUS_CODE, message);
    }
}
