package com.example.auth.repository.jpa;

import com.example.auth.domain.OauthConfig;
import com.example.auth.domain.OauthConfigKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthConfigJpaRepository extends JpaRepository<OauthConfig, OauthConfigKey> {
}
