package com.rampagemc.authorization.service;

import com.rampagemc.authorization.configuraiton.property.JwtProperties;
import com.rampagemc.authorization.domain.entity.JwtToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenerateJwtServiceImpl implements GenerateJwtService {
    private final JwtProperties jwtProperties;

    @Override
    public JwtToken generateToken(String username) {
        Key hmacKey = new SecretKeySpec(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS256.getJcaName());
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from(Instant.now().plus(jwtProperties.getExpirationSeconds(), ChronoUnit.SECONDS)))
                .signWith(hmacKey)
                .compact();
        log.info("Token was generated for {}", username);
        return new JwtToken(jwt);
    }
}
