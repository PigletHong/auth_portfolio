package com.example.auth.repository;

import com.example.auth.domain.OauthConfig;
import com.example.auth.domain.ProjectInformation;

import java.util.Optional;

public interface ConfigRepository {
    Optional<ProjectInformation> getProjectInformation(String projectId);
    Optional<OauthConfig> getOauthConfig(String projectId, String provider);
    void setOauthConfig(OauthConfig oauthConfig);
}
