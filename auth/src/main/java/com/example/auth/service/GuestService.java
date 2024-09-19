package com.example.auth.service;

import com.example.auth.domain.Account;
import com.example.auth.domain.Guest;
import com.example.auth.domain.ProjectLink;
import com.example.auth.dto.RequestDto;
import com.example.auth.dto.ResponseDto;
import com.example.auth.repository.ProjectLinkRepository;
import com.example.auth.util.exception.CustomException;
import com.example.auth.util.exception.StatusCode;
import com.example.auth.repository.AccountRepository;
import com.example.auth.repository.GuestRepository;
import com.example.auth.util.jwt.TokenManager;
import com.example.auth.util.uuid.UuidGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final TokenManager tokenManager;
    private final GuestRepository guestRepository;
    private final AccountRepository accountRepository;
    private final ProjectLinkRepository projectLinkRepository;

    public ResponseDto.TokenResponse signUpGuest(String projectId, RequestDto.GuestSignRequest request) {

        Optional<Guest> existGuest = guestRepository.findById(request.getDeviceId());
        existGuest.ifPresent(guest -> {
            throw new CustomException(StatusCode.ExistGuest);
        });

        Guest guest = Guest.builder()
                .deviceId(request.getDeviceId())
                .accountKey(UuidGenerator.generateUuid())
                .linkKey(UuidGenerator.generateUuid())
                .build();

        ProjectLink projectLink = ProjectLink.builder()
                .projectId(projectId)
                .accountKey(guest.getAccountKey())
                .linkKey(guest.getLinkKey())
                .build();

        Account account = Account.builder()
                .accountKey(guest.getAccountKey())
                .build();

        guestRepository.save(guest);
        projectLinkRepository.save(projectLink);
        Account savedAccount = accountRepository.save(account);

        String accessToken = tokenManager.CreateAccessToken(savedAccount.getUid());

        return ResponseDto.TokenResponse.builder().accessToken(accessToken).build();
    }

    public ResponseDto.TokenResponse signInGuest(String projectId, RequestDto.GuestSignRequest request) {
        Optional<Guest> existGuest = guestRepository.findById(request.getDeviceId());
        Guest guest = existGuest.orElseThrow(() -> new CustomException(StatusCode.NotExistGuest));
        projectLinkRepository.findProjectLinkByLinkKey(guest.getLinkKey()).orElseThrow(() -> new CustomException(StatusCode.NotExistProjectLink));
        Account account = accountRepository.findByAccountKey(guest.getAccountKey()).orElseThrow(() -> new RuntimeException("Account not found"));
        String accessToken = tokenManager.CreateAccessToken(account.getUid());
        return ResponseDto.TokenResponse.builder().accessToken(accessToken).build();
    }
}
