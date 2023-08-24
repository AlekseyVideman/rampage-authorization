package com.rampagemc.authorization.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private final String username;
    private final String password;
    private JwtToken token;

    public User(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }
}
