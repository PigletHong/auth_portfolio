package com.example.auth.util.jwt;

import com.example.auth.repository.redis.RedisRepository;
import com.example.auth.util.exception.CustomException;
import com.example.auth.util.exception.StatusCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenManager {
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String ISSUER = "PigletHong";
    private static final String AUDIENCE = "PigletHong";
    @Value("${private.key.path}")
    private String FILENAME;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    @PostConstruct
    private void init() {
        this.privateKey = this.getPrivateKey();
        this.publicKey = this.getPublicKey();
    }

    public String createAccessToken(long uid) {
        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(uid))
                .setAudience(AUDIENCE)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1일
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
        return TOKEN_PREFIX + accessToken;
    }

    public boolean validateAccessToken(String token) {
        try {
            String tokenWithoutPrefix = token.replace(TOKEN_PREFIX, "");

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .requireAudience(AUDIENCE)
                    .requireIssuer(ISSUER)
                    .build()
                    .parseClaimsJws(tokenWithoutPrefix)
                    .getBody();

            // 만료 시간을 확인합니다.
            Date expiration = claims.getExpiration();
            if (expiration != null && expiration.before(new Date())) {
                throw new CustomException(StatusCode.ExpiredToken);
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException(StatusCode.InvalidToken);
        }
    }

    private PublicKey getPublicKey() {
        try {
            RSAPrivateCrtKey privk = (RSAPrivateCrtKey)privateKey;
            RSAPublicKeySpec publicKeySpec = new java.security.spec.RSAPublicKeySpec(privk.getModulus(), privk.getPublicExponent());

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private PrivateKey getPrivateKey() {
        try{
            ClassPathResource resource = new ClassPathResource(FILENAME);
            String key = new String(Files.readAllBytes(resource.getFile().toPath()));
            String privateKeyPEM = key
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace(System.lineSeparator(), "")
                    .replace("-----END PRIVATE KEY-----", "");
            return this.CreatePrivateKey(privateKeyPEM);
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PrivateKey CreatePrivateKey(String privateKeyPEM) {
        try{
            byte[] encoded = java.util.Base64.getDecoder().decode(privateKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
