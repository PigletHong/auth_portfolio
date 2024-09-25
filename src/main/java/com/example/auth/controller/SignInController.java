package com.example.auth.controller;

import com.example.auth.aop.annotation.ProjectIdValidate;
import com.example.auth.dto.RequestDto;
import com.example.auth.dto.ResponseDto;
import com.example.auth.service.GoogleService;
import com.example.auth.service.GuestService;
import com.example.auth.util.exception.GlobalResponseDto;
import com.example.auth.util.exception.ResponseUtil;
import com.example.auth.util.exception.StatusCode;
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
        return ResponseUtil.response(guestService.signInGuest(projectId, request));
    }

    @ProjectIdValidate
    @PostMapping("/auth/api/{projectId}/signin/google")
    public ResponseEntity<GlobalResponseDto> signInGoogle(@PathVariable String projectId, @RequestBody RequestDto.SocialSignRequest request) {
        googleService.signInByIdToken(projectId, request);
        return ResponseUtil.response(StatusCode.OK);
    }
}
