package com.rampagemc.authorization.model.service;

import com.rampagemc.authorization.model.entity.JwtToken;

public interface JwtTokenService {
    JwtToken generateToken(String username, String password);
}
