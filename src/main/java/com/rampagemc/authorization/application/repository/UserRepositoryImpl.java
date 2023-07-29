package com.rampagemc.authorization.application.repository;

import com.rampagemc.authorization.application.integration.limboauth.LimboAuthUser;
import com.rampagemc.authorization.model.entity.User;
import com.rampagemc.authorization.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private ApplicationLimboUserRepository repository;

    @Override
    public void save(User user) {
        repository.save(new LimboAuthUser(user.getUsername(), user.getPassword()));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var limboUser = repository.findByLowercaseNickname(username);
        if (limboUser.isPresent()) {
            var limboAuthUser = limboUser.get();
            var user = new User(limboAuthUser.getNickname(), limboAuthUser.getHash());
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
