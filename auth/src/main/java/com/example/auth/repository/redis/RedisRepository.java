package com.example.auth.repository.redis;

import com.example.auth.domain.ProjectInformation;
import com.example.auth.util.exception.CustomException;
import com.example.auth.util.exception.StatusCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisRepository {
    private static final String PROJECT_INFO_PREFIX = "auth:project-info:";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;


    private void setValues(String key, String data) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data);
    }

    private void setValues(String key, String data, int seconds) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data, Duration.ofSeconds(seconds));
    }

    private String getValues(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get(key) == null) {
            return "false";
        }
        return (String) values.get(key);
    }

    private void deleteValues(String key) {
        redisTemplate.delete(key);
    }

    public Optional<ProjectInformation> getProjectInformation(String projectId) {
        try {
            String cachedData = this.getValues(PROJECT_INFO_PREFIX + projectId);
            if (cachedData == null || cachedData.equals("false")) {
                return Optional.empty();
            }
            return Optional.ofNullable(objectMapper.readValue(cachedData, ProjectInformation.class));
        } catch (JsonProcessingException e) {
            throw new CustomException(StatusCode.InvalidParsingType);
        }
    }

    public void setProjectInformation(String projectId, ProjectInformation projectInformation) {
        try {
            String jsonString = objectMapper.writeValueAsString(projectInformation);
            log.info(jsonString);
            this.setValues(PROJECT_INFO_PREFIX + projectId, jsonString, 3600);
        } catch (JsonProcessingException e) {
            throw new CustomException(StatusCode.InvalidParsingType);
        }
    }
}
