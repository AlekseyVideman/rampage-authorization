package com.rampagemc.authorization.model.service;

import com.rampagemc.authorization.dto.UserDto;
import com.rampagemc.authorization.model.entity.User;
import com.rampagemc.authorization.model.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class DomainUserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtTokenService jwtTokenService;

    public User registerUser(@NotNull UserDto userDto) throws ResponseStatusException {
        boolean isExists = repository.findByUsername(userDto.username().toLowerCase(Locale.ROOT)).isPresent();
        if (isExists) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User already registered");
        }
        var user = new User(userDto.username(), passwordEncoder.encode(userDto.password()));
            user.setToken(jwtTokenService.generateToken(userDto.username(), userDto.password()));
        repository.save(user);
        return user;
    }
}
