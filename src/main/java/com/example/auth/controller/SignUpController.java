package com.example.auth.controller;

import com.example.auth.aop.annotation.ProjectIdValidate;
import com.example.auth.dto.RequestDto;
import com.example.auth.dto.ResponseDto;
import com.example.auth.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final GuestService guestService;

    @ProjectIdValidate
    @PostMapping("/auth/api/{projectId}/signup/guest")
    public ResponseEntity<ResponseDto.TokenResponse> signUpGuest(@PathVariable String projectId, @RequestBody RequestDto.GuestSignRequest request) {
        return ResponseEntity.ok(guestService.signUpGuest(projectId, request));
    }
}
