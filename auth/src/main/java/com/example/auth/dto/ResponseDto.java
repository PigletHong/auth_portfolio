package com.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

public class ResponseDto {
    @Getter
    @Builder
    public static class TokenResponse {
        @JsonProperty("access_token")
        private String accessToken;
    }
}
