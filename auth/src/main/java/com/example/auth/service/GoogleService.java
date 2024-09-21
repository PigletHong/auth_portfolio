package com.example.auth.service;

import com.example.auth.repository.SocialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleService {
    private final SocialRepository socialRepository;
}
