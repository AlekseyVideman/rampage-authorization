package com.rampagemc.authorization.service;

import com.rampagemc.authorization.exception.UserNotRegistered;
import com.rampagemc.authorization.repository.LimboUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SpringUserDetailsService implements UserDetailsService {
    private final LimboUserRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
        var limboUser = repository.findByLowercaseNickname(username.toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new UserNotRegistered(username));
        return User.builder()
                .username(limboUser.getNickname())
                .password(limboUser.getHash())
                .roles("USER")
                .build();
    }
}
