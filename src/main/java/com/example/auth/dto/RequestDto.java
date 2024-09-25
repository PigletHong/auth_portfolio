package com.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RequestDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(force = true)
    public static class GuestSignRequest {
        @JsonProperty("device_id")
        private String deviceId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(force = true)
    public static class SocialSignRequest {
        @JsonProperty("type")
        private String type;
        @JsonProperty("value")
        private String value;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(force = true)
    public static class GoogleOauthConfigRequest {
        @JsonProperty("client_id")
        private String clientId;
        @JsonProperty("client_secret")
        private String clientSecret;
        @JsonProperty("redirect_uri")
        private String redirectUri;
        @JsonProperty("scope")
        private String scope;
    }
}
