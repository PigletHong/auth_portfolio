package com.example.auth.aop.aspect;

import com.example.auth.service.ConfigService;
import com.example.auth.util.exception.CustomException;
import com.example.auth.util.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ProjectValidateAspect {
    private final ConfigService configService;

    @Before("@annotation(com.example.auth.aop.annotation.ProjectIdValidate) && args(projectId, ..)")
    public void validateProjectId(String projectId) {
        this.configService.getProjectInformation(projectId)
                .orElseThrow(() -> new CustomException(StatusCode.InvalidProjectId));
    }
}
