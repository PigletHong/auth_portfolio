package com.example.auth.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_auth_oauth_config")
@IdClass(OauthConfigKey.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OauthConfig {
    @Id
    @Column(name = "project_id", nullable = false)
    private String projectId;

    @Id
    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "client_data", nullable = false)
    public String clientDate;

    @Column(name = "redirect_uri", nullable = false)
    public String redirectUri;

    @Column(name = "reg_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regDate;
}
