package com.example.auth.service;

import com.example.auth.domain.OauthConfig;
import com.example.auth.domain.ProjectInformation;
import com.example.auth.repository.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigService {
    private final ConfigRepository configRepository;

    public Optional<ProjectInformation> getProjectInformation(String projectId) {
        return configRepository.getProjectInformation(projectId);
    }

    public Optional<OauthConfig> getOauthConfig(String projectId, String provider) {
        return configRepository.getOauthConfig(projectId, provider);
    }
}
