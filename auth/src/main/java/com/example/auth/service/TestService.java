package com.example.auth.service;

import com.example.auth.domain.Social;
import com.example.auth.repository.TestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    @Transactional
    public void test() {
        Social social = Social.builder()
                .serviceId("test")
                .provider("test")
                .socialId("test")
                .accountKey("test")
                .linkKey("test")
                .build();

        testRepository.save(social);
    }
}
