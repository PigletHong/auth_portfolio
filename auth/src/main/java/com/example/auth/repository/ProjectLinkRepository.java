package com.example.auth.repository;

import com.example.auth.domain.ProjectLink;

import java.util.Optional;

public interface ProjectLinkRepository {
    Optional<ProjectLink> getProjectLink(String linkKey);
    void saveProjectLink(ProjectLink projectLink);
}
