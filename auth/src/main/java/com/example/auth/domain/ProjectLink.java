package com.example.auth.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_auth_project_linkage")
@IdClass(ProjectLinkKey.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProjectLink {
    @Id
    @Column(name = "project_id", nullable = false)
    private String projectId;

    @Id
    @Column(name = "account_key", nullable = false)
    private String accountKey;

    @Id
    @Column(name = "link_key", nullable = false)
    private String linkKey;

    @Column(name = "reg_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regDate;
}
