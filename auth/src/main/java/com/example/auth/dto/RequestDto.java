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
}
