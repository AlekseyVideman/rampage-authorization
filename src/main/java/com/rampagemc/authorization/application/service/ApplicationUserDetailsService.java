package com.rampagemc.authorization.application.service;

import com.rampagemc.authorization.application.repository.ApplicationLimboUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Locale;

public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private ApplicationLimboUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var limboUser = repository.findByLowercaseNickname(username.toLowerCase(Locale.ROOT)).orElseThrow(() -> new UsernameNotFoundException("User not registered"));
        System.out.println("limboUser = " + limboUser);
        return User.builder()
                .username(limboUser.getNickname())
                .password(limboUser.getHash())
                .build();
    }
}
