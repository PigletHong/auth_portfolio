package com.example.auth.dto;

import lombok.Builder;
import lombok.Getter;

public class ResponseDto {
    @Getter
    @Builder
    public static class TokenResponse {
        private String accessToken;
    }
}
