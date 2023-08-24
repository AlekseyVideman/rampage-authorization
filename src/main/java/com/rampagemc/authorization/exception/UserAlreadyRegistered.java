package com.rampagemc.authorization.exception;

public class UserAlreadyRegistered extends RuntimeException {
    public UserAlreadyRegistered(String username) {
        super(String.format("User %s is already registered!", username));
    }
}
