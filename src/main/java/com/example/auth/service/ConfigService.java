package com.example.auth.service;

import com.example.auth.domain.GoogleClientData;
import com.example.auth.domain.OauthConfig;
import com.example.auth.domain.ProjectInformation;
import com.example.auth.dto.RequestDto;
import com.example.auth.repository.ConfigRepository;
import com.example.auth.util.exception.CustomException;
import com.example.auth.util.exception.StatusCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigService {
    private final ConfigRepository configRepository;
    private final ObjectMapper objectMapper;

    public Optional<ProjectInformation> getProjectInformation(String projectId) {
        return configRepository.getProjectInformation(projectId);
    }

    public Optional<OauthConfig> getOauthConfig(String projectId, String provider) {
        return configRepository.getOauthConfig(projectId, provider);
    }

    public void setOauthConfig(String projectId, RequestDto.GoogleOauthConfigRequest request) {
        try {
            GoogleClientData googleClientData = GoogleClientData.builder()
                    .clientId(request.getClientId())
                    .clientSecret(request.getClientSecret())
                    .build();

            OauthConfig googleConfig = OauthConfig.builder()
                    .projectId(projectId)
                    .provider("google")
                    .clientDate(this.objectMapper.writeValueAsString(googleClientData))
                    .redirectUri(request.getRedirectUri())
                    .scope(request.getScope())
                    .build();
            this.configRepository.setOauthConfig(googleConfig);
        } catch (JsonProcessingException e) {
            throw new CustomException(StatusCode.InvalidToken);
        }
    }
}
