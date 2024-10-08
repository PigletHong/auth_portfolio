package com.example.auth.repository.impl;

import com.example.auth.domain.OauthConfig;
import com.example.auth.domain.OauthConfigKey;
import com.example.auth.domain.ProjectInformation;
import com.example.auth.repository.ConfigRepository;
import com.example.auth.repository.jpa.OauthConfigJpaRepository;
import com.example.auth.repository.jpa.ProjectInfoJpaRepository;
import com.example.auth.repository.redis.RedisRepository;
import com.example.auth.util.exception.CustomException;
import com.example.auth.util.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConfigRepositoryImpl implements ConfigRepository {

    private final ProjectInfoJpaRepository projectInfoJpaRepository;
    private final OauthConfigJpaRepository oauthConfigJpaRepository;
    private final RedisRepository redisRepository;

    @Override
    public Optional<ProjectInformation> getProjectInformation(String projectId) {
        Optional<ProjectInformation> cachedProjectInformation = this.redisRepository.getProjectInformation(projectId);
        if (cachedProjectInformation.isPresent()) {
            return cachedProjectInformation;
        }
        Optional<ProjectInformation> projectInformation = this.projectInfoJpaRepository.findById(projectId);
        projectInformation.orElseThrow(() -> new CustomException(StatusCode.InvalidProjectId));

        this.redisRepository.setProjectInformation(projectId, projectInformation.get());
        return projectInformation;
    }

    @Override
    public Optional<OauthConfig> getOauthConfig(String projectId, String provider) {
        Optional<OauthConfig> cachedConfig = this.redisRepository.getOauthConfig(projectId, provider);
        if (cachedConfig.isPresent()) {
            return cachedConfig;
        }
        OauthConfigKey key = new OauthConfigKey(projectId, provider);
        Optional<OauthConfig> oauthConfig = this.oauthConfigJpaRepository.findById(key);
        oauthConfig.orElseThrow(() -> new CustomException(StatusCode.NotExistOauthConfig));

        this.redisRepository.setOauthConfig(projectId, provider, oauthConfig.get());
        return oauthConfig;
    }

    @Override
    public void setOauthConfig(OauthConfig oauthConfig) {
        this.oauthConfigJpaRepository.save(oauthConfig);
    }
}
