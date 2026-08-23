package com.tracen.dyp.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "tracen-dyp-secret-key-must-be-at-least-32-characters-long";

    private static final long EXPIRATION_TIME =
            1000L * 60 * 60 * 24;

    private final SecretKey signingKey =
            Keys.hmacShaKeyFor(
                    SECRET_KEY.getBytes(StandardCharsets.UTF_8)
            );

    public String generateToken(Long userId, String email) {

        Date now = new Date();

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("email", email)
                .issuedAt(now)
                .expiration(
                        new Date(now.getTime() + EXPIRATION_TIME)
                )
                .signWith(signingKey)
                .compact();
    }

    public Long extractUserId(String token) {

        String subject = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

        return Long.valueOf(subject);
    }

    public boolean isTokenValid(String token) {

        try {
            Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}