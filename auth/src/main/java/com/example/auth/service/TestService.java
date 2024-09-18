package com.example.auth.service;

import com.example.auth.domain.Social;
import com.example.auth.domain.SocialKey;
import com.example.auth.repository.RedisRepository;
import com.example.auth.repository.TestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    private final RedisRepository redisRepository;
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

    public void getTest() {
        SocialKey socialKey = SocialKey.builder()
                .serviceId("test")
                .provider("test")
                .socialId("test")
                .build();

        Optional<Social> social = testRepository.findById(socialKey);
        social.ifPresent(s -> {
            System.out.println("Social entity found: " + s.getSocialId());
        });
    }

    public void redisTest() {
        redisRepository.setValues("test", "test", 300);
        String data = redisRepository.getValues("test");
        System.out.println(data);
    }
}
