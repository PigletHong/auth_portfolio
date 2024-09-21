package com.example.auth.controller;

import com.example.auth.aop.annotation.ProjectIdValidate;
import com.example.auth.dto.RequestDto;
import com.example.auth.dto.ResponseDto;
import com.example.auth.service.GoogleService;
import com.example.auth.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignInController {
    private final GuestService guestService;
    private final GoogleService googleService;

    @ProjectIdValidate
    @PostMapping("/auth/api/{projectId}/signin/guest")
    public ResponseEntity<ResponseDto.TokenResponse> signInGuest(@PathVariable String projectId, @RequestBody RequestDto.GuestSignRequest request) {
        return ResponseEntity.ok(guestService.signInGuest(projectId, request));
    }

    /*
    @ProjectIdValidate
    @PostMapping("/auth/api/{projectId}/signin/google")
    public ResponseEntity<ResponseDto.TokenResponse> signInGoogle(@PathVariable String projectId, @RequestBody RequestDto.SocialSignRequest request) {
        return ResponseEntity.ok(googleService.signInGoogle(projectId, request));
    }
    */
}
