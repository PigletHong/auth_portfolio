package com.example.auth.repository;

import com.example.auth.domain.Social;
import com.nimbusds.jose.jwk.JWK;

import java.util.List;
import java.util.Optional;

public interface SocialRepository {
    Optional<List<JWK>> getOauthJsonWebKeySet(String provider);
    Optional<Social> getSocial(String socialId);
}
