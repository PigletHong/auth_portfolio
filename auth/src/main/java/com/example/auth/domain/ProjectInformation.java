package com.example.auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_auth_project_information")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProjectInformation {
    @Id
    @Column(name = "project_id", nullable = false)
    private String projectId;

    @Column(name = "project_display_name", nullable = false)
    private String displayName;

    @Column(name = "reg_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regDate;
}
