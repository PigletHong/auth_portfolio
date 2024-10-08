package com.example.auth.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    // 메세지 응답 (No Data)
    public static ResponseEntity<GlobalResponseDto> response(StatusCode statusCode) {

        if (statusCode.getStatusCode() == 200) {
            // 성공응답
            return new ResponseEntity<>(new GlobalResponseDto<>(statusCode), HttpStatus.OK);
        } else {
            // 예외응답
            return new ResponseEntity<>(new GlobalResponseDto<>(statusCode), statusCode.getHttpStatus());
        }
    }

    public static <T> ResponseEntity<T> response(T data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // 성공 메세지와 응답 (Data)
    public static <T> ResponseEntity<GlobalResponseDto<T>> response(StatusCode statusCode, T data) {
        return new ResponseEntity<>(new GlobalResponseDto<>(statusCode, data), statusCode.getHttpStatus());
    }
}
