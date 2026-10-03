package com.example.demo.exception;

public class InvalidRequestException extends BusinessException {

    public InvalidRequestException(String message) {
        super(400, message);
    }
}