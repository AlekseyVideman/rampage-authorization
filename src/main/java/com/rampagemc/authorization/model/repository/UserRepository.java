package com.rampagemc.authorization.model.repository;

import com.rampagemc.authorization.model.entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByUsername(String username);
}
