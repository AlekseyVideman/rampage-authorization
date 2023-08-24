package com.rampagemc.authorization.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotRegistered extends AuthenticationException {
    public UserNotRegistered(String username) {
        super(String.format("User %s is not registered!", username));
    }
}
