package com.example.auth.service;

import com.example.auth.repository.SocialRepository;
import com.example.auth.util.exception.CustomException;
import com.example.auth.util.exception.StatusCode;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleService {
    private final SocialRepository socialRepository;

    public void signInByIdToken(String idToken) {
        boolean validateResult = this.validateIdToken(idToken);
        if (validateResult) {
            String socialId = this.getSocialId(idToken);
            log.info("Google User ID: " + socialId);
        } else {
            throw new CustomException(StatusCode.InvalidToken);
        }
    }

    private String getSocialId(String idToken) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(idToken);
            return signedJWT.getJWTClaimsSet().getSubject(); // subject 값 추출
        } catch (ParseException e) {
            throw new CustomException(StatusCode.InvalidToken);
        }
    }

    private boolean validateIdToken(String idToken) {
        try {
            Optional<List<JWK>> googleJsonWebKeySet = this.socialRepository.getOauthJsonWebKeySet("google");
            List<JWK> jwkList = googleJsonWebKeySet.orElseThrow(() -> new CustomException(StatusCode.InvalidParsingType));

            SignedJWT signedJWT = SignedJWT.parse(idToken);

            String keyId = signedJWT.getHeader().getKeyID();
            JWKSet jwkSet = new JWKSet(jwkList);
            JWK matchingKey = jwkSet.getKeyByKeyId(keyId);
            if (matchingKey == null) {
                throw new CustomException(StatusCode.InvalidParsingType);
            }

            RSAKey rsaKey = (RSAKey) matchingKey;
            JWSVerifier verifier = new RSASSAVerifier(rsaKey.toRSAPublicKey());
            boolean isVerified = signedJWT.verify(verifier);

            if (!isVerified) {
                throw new CustomException(StatusCode.InvalidParsingType);
            }

            return true;
        } catch (ParseException | JOSEException e) {
            throw new CustomException(StatusCode.InvalidToken);
        }
    }
}
