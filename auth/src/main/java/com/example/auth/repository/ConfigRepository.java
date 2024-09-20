package com.example.auth.repository;

import com.example.auth.domain.ProjectInformation;

import java.util.Optional;

public interface ConfigRepository {
    Optional<ProjectInformation> getProjectInformation(String projectId);
}
