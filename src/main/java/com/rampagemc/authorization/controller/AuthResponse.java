package com.rampagemc.authorization.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthResponse {
    private String username;
    private String jwt_token;
}
