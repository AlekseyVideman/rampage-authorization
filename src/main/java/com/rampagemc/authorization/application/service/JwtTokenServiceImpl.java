package com.rampagemc.authorization.application.service;

import com.rampagemc.authorization.model.entity.JwtToken;
import com.rampagemc.authorization.model.service.JwtTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {
    @Value("${authorization.jwt.expiration-seconds}")
    private Long expirationPeriod;

    @Value("${authorization.jwt.secret}")
    private String secret;

    @Override
    public JwtToken generateToken(String username, String password) {
        Key hmacKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS256.getJcaName());
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from(Instant.now().plus(expirationPeriod, ChronoUnit.SECONDS)))
                .signWith(hmacKey)
                .compact();
        return new JwtToken(jwt);
    }
}
