package com.example.auth.controller;

import com.example.auth.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping("/test")
    public void test() {
        testService.test();
    }

    @GetMapping("/test")
    public void getTest() {
        testService.getTest();
    }

    @PostMapping("/redis")
    public void redisTest() {
        testService.redisTest();
    }
}
