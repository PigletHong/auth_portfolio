package com.example.auth.repository.jpa;

import com.example.auth.domain.ProjectLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectLinkJpaRepository extends JpaRepository<ProjectLink, Long> {
    Optional<ProjectLink> findProjectLinkByLinkKey(String linkKey);
}
