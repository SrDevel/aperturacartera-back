package com.registrocartera.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.registrocartera.model.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String jwtSecret;

    public String generateToken(AppUser user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.create()
                    .withIssuer("Minimercado el Rubi")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generando token");
        }
    }

    public String getSubject(String token) {
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("Minimercado el Rubi")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error verificando token");
        }
        if (verifier == null) {
            throw new RuntimeException("Error verificando token");
        }

        return verifier.getSubject();
    }

    private Instant generateDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-05:00"));
    }
}