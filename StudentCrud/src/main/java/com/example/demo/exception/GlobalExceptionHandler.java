package com.example.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.common.MessageConstants;
import com.example.demo.common.ResponseModel;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseModel handleCustomAppException(CustomException ex) {
        return new ResponseModel(MessageConstants.FAILED, ex.getStatusCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseModel handleGenericException(Exception ex) {
        return new ResponseModel(MessageConstants.FAILED, "500", "Unexpected error: " + ex.getMessage());
    }
}
