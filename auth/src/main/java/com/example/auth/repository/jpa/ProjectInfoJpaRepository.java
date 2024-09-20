package com.example.auth.repository.jpa;

import com.example.auth.domain.ProjectInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectInfoJpaRepository extends JpaRepository<ProjectInformation, String> {
}
