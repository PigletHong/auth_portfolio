package com.example.auth.repository.jpa;

import com.example.auth.domain.Social;
import com.example.auth.domain.SocialKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialJpaRepository extends JpaRepository<Social, SocialKey> {
    Optional<Social> findBySocialId(String socialId);
}
