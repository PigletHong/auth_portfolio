package com.example.auth.controller;

import com.example.auth.dto.RequestDto;
import com.example.auth.dto.ResponseDto;
import com.example.auth.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final GuestService guestService;

    @PostMapping("/auth/api/{projectId}/signup/guest")
    public ResponseDto.TokenResponse signUpGuest(@PathVariable String projectId, @RequestBody RequestDto.GuestSignRequest request) throws Exception {
        return guestService.signUpGuest(projectId, request);
    }
}
