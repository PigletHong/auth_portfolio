package com.example.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OauthConfigKey implements Serializable {
    private String projectId;
    private String provider;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OauthConfigKey that = (OauthConfigKey) o;
        return projectId.equals(that.projectId) && provider.equals(that.provider);
    }

    @Override
    public int hashCode() { return Objects.hash(projectId, provider); }
}
