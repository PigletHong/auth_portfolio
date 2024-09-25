package com.example.auth.controller;

import com.example.auth.aop.annotation.ProjectIdValidate;
import com.example.auth.dto.RequestDto;
import com.example.auth.service.ConfigService;
import com.example.auth.util.exception.GlobalResponseDto;
import com.example.auth.util.exception.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.example.auth.util.exception.StatusCode.OK;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final ConfigService configService;

    @ProjectIdValidate
    @PostMapping("/admin/api/{projectId}/google/oauth/config")
    public ResponseEntity<GlobalResponseDto> saveGoogleOauthConfig(@PathVariable String projectId, @RequestBody RequestDto.GoogleOauthConfigRequest request) {
        this.configService.setOauthConfig(projectId, request);
        return ResponseUtil.response(OK);
    }
}
