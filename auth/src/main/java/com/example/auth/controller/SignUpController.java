package com.example.auth.controller;

import com.example.auth.util.jwt.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final TokenManager tokenManager;

    @PostMapping("/auth/api/{projectId}/signup/guest")
    public String signUpGuest(@PathVariable String projectId) throws Exception {
        return tokenManager.CreateAccessToken(1);
    }
}
