package com.example.auth.util.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponseDto<T> {
    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String statusMsg;
    private T data;

    public GlobalResponseDto(StatusCode statusCode) {
        this.httpStatus = statusCode.getHttpStatus();
        this.statusCode = statusCode.getStatusCode();
        this.statusMsg = statusCode.getStatusMsg();
    }

    public GlobalResponseDto(StatusCode statusCode, T data) {
        this.httpStatus = statusCode.getHttpStatus();
        this.statusCode = statusCode.getStatusCode();
        this.statusMsg = statusCode.getStatusMsg();
        this.data = data;
    }
}
