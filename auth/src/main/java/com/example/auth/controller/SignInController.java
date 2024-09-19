package com.example.auth.controller;

import com.example.auth.dto.RequestDto;
import com.example.auth.dto.ResponseDto;
import com.example.auth.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignInController {
    private final GuestService guestService;

    @PostMapping("/auth/api/{projectId}/signin/guest")
    public ResponseDto.TokenResponse signInGuest(@PathVariable String projectId, @RequestBody RequestDto.GuestSignRequest request) throws Exception {
        return guestService.signInGuest(projectId, request);
    }
}
