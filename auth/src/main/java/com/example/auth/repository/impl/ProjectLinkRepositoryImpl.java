package com.example.auth.repository.impl;

import com.example.auth.domain.ProjectLink;
import com.example.auth.repository.ProjectLinkRepository;
import com.example.auth.repository.jpa.ProjectLinkJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProjectLinkRepositoryImpl implements ProjectLinkRepository {

    private final ProjectLinkJpaRepository projectLinkJpaRepository;

    @Override
    public Optional<ProjectLink> getProjectLink(String linkKey) {
        return projectLinkJpaRepository.findProjectLinkByLinkKey(linkKey);
    }

    @Override
    public void saveProjectLink(ProjectLink projectLink) {
        projectLinkJpaRepository.save(projectLink);
    }
}
