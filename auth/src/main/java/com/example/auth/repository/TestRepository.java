package com.example.auth.repository;

import com.example.auth.domain.Social;
import com.example.auth.domain.SocialKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Social, SocialKey> {

}
