package com.rampagemc.authorization.service;

import com.rampagemc.authorization.domain.entity.JwtToken;

public interface GenerateJwtService {
    JwtToken generateToken(String username, String id);
}
