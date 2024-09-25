package com.example.auth.util.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final StatusCode statusCode;

    public CustomException(StatusCode statusCode){
        super(statusCode.getStatusMsg());
        this.statusCode = statusCode;
    }
}