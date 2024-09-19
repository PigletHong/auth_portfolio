package com.example.auth.service;

import com.example.auth.domain.Account;
import com.example.auth.domain.Guest;
import com.example.auth.dto.RequestDto;
import com.example.auth.dto.ResponseDto;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.StatusCode;
import com.example.auth.repository.AccountRepository;
import com.example.auth.repository.GuestRepository;
import com.example.auth.util.jwt.TokenManager;
import com.example.auth.util.uuid.UuidGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final TokenManager tokenManager;
    private final GuestRepository guestRepository;
    private final AccountRepository accountRepository;

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

        Account account = Account.builder()
                .accountKey(guest.getAccountKey())
                .build();

        guestRepository.save(guest);
        Account savedAccount = accountRepository.save(account);
        String accessToken = tokenManager.CreateAccessToken(savedAccount.getUid());

        return ResponseDto.TokenResponse.builder().accessToken(accessToken).build();
    }

    public ResponseDto.TokenResponse signInGuest(String projectId, RequestDto.GuestSignRequest request) {
        Optional<Guest> existGuest = guestRepository.findById(request.getDeviceId());
        Guest guest = existGuest.orElseThrow(() -> new CustomException(StatusCode.NotExistGuest));
        Account account = accountRepository.findByAccountKey(guest.getAccountKey()).orElseThrow(() -> new RuntimeException("Account not found"));
        String accessToken = tokenManager.CreateAccessToken(account.getUid());
        return ResponseDto.TokenResponse.builder().accessToken(accessToken).build();
    }
}
