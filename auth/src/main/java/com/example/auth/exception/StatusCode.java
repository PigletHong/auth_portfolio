package com.example.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StatusCode {
    ExistGuest(HttpStatus.BAD_REQUEST, 1000, "이미 존재하는 게스트 계정입니다."),
    NotExistGuest(HttpStatus.BAD_REQUEST, 1001, "존재하지 않는 게스트 계정입니다."),

    OK(HttpStatus.OK, 200, "응답이 정상 처리 되었습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String statusMsg;
}
