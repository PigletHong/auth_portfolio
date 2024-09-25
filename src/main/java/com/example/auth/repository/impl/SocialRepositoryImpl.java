package com.example.auth.repository.impl;

import com.example.auth.domain.Social;
import com.example.auth.repository.SocialRepository;
import com.example.auth.repository.jpa.SocialJpaRepository;
import com.example.auth.repository.redis.RedisRepository;
import com.example.auth.util.exception.CustomException;
import com.example.auth.util.exception.StatusCode;
import com.nimbusds.jose.RemoteKeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocialRepositoryImpl implements SocialRepository {

    @Value("${google.oauth2.jwks-uri}")
    private String googleOauth2JwksUri;
    private final RedisRepository redisRepository;
    private final SocialJpaRepository socialJpaRepository;

    @Override
    public Optional<List<JWK>> getOauthJsonWebKeySet(String provider) {
        try {
            Optional<List<JWK>> cachedJWKS = this.redisRepository.getOauthJsonWebKeySet(provider);
            if (cachedJWKS.isPresent()) {
                return cachedJWKS;
            }

            var remoteJWKSet = new RemoteJWKSet<>(new URL(this.getOauthWebKeySetUri(provider)));
            List<JWK> jwkList = remoteJWKSet.get(new JWKSelector(new JWKMatcher.Builder().build()), null);

            this.redisRepository.setOauthJsonWebKeySet(provider, jwkList);

            return Optional.of(jwkList);
        } catch (MalformedURLException | RemoteKeySourceException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Social> getSocial(String socialId) {
        return socialJpaRepository.findBySocialId(socialId);
    }

    private String getOauthWebKeySetUri(String provider) {
        return switch (provider) {
            case "google" -> googleOauth2JwksUri;
            case "apple" -> googleOauth2JwksUri;
            case "facebook" -> googleOauth2JwksUri;
            default -> throw new CustomException(StatusCode.ExistGuest);
        };
    }
}
