package com.example.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialKey implements Serializable {
    private String serviceId;
    private String provider;
    private String socialId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialKey socialKey = (SocialKey) o;
        return serviceId.equals(socialKey.serviceId) && provider.equals(socialKey.provider) && socialId.equals(socialKey.socialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, provider, socialId);
    }
}