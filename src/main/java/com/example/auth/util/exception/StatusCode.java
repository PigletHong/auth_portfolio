package com.example.auth.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StatusCode {
    ExistGuest(HttpStatus.BAD_REQUEST, 1000, "이미 존재하는 게스트 계정입니다."),
    NotExistGuest(HttpStatus.BAD_REQUEST, 1001, "존재하지 않는 게스트 계정입니다."),
    NotExistProjectLink(HttpStatus.BAD_REQUEST, 1002, "프로젝트에 가입이 되어 있지 않은 계정입니다."),

    ExpiredToken(HttpStatus.BAD_REQUEST, 1003, "만료된 토큰입니다."),
    InvalidToken(HttpStatus.BAD_REQUEST, 1004, "올바르지 않은 토큰입니다."),
    InvalidProjectId(HttpStatus.BAD_REQUEST, 1005, "등록되지 않은 프로젝트 ID 입니다."),
    InvalidParsingType(HttpStatus.BAD_REQUEST, 1006, "변환 타입이 맞지 않습니다."),
    NotExistOauthConfig(HttpStatus.BAD_REQUEST, 1007, "프로젝트에 해당하는 Oauth 설정이 없습니다."),


    OK(HttpStatus.OK, 200, "응답이 정상 처리 되었습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String statusMsg;
}
