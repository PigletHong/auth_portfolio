package com.example.auth.repository.impl;

import com.example.auth.domain.ProjectInformation;
import com.example.auth.repository.ConfigRepository;
import com.example.auth.repository.jpa.ProjectInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConfigRepositoryImpl implements ConfigRepository {

    private final ProjectInfoJpaRepository projectInfoJpaRepository;

    @Override
    public Optional<ProjectInformation> getProjectInformation(String projectId) {
        return projectInfoJpaRepository.findById(projectId);
    }
}
