package com.rampagemc.authorization.service;

import com.rampagemc.authorization.dto.request.UserDto;
import com.rampagemc.authorization.mapper.UserLimboUserMapper;
import com.rampagemc.authorization.domain.entity.User;
import com.rampagemc.authorization.exception.UserAlreadyRegistered;
import com.rampagemc.authorization.repository.LimboUserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterUserService {
    private final PasswordEncoder passwordEncoder;
    private final UserLimboUserMapper limboUserMapper;
    private final GenerateJwtService generateJwtService;
    private final LimboUserRepository userRepository;

    @Transactional
    public void registerUser(@NotNull UserDto userDto) {
        if (userRepository.existsById(userDto.username().toLowerCase(Locale.ROOT))) {
            throw new UserAlreadyRegistered(userDto.username());
        }
        val user = new User(userDto.username(), passwordEncoder.encode(userDto.password()));
        userRepository.save(limboUserMapper.toLimboUser(user));

        log.info("User {} just registered", userDto.username());
    }

    public User getRegisteredUser(String name) {
        val limboAuthUser = userRepository.findByLowercaseNickname(name.toLowerCase(Locale.ROOT)).get(); // value is guaranteed to be not null
        val uuid = limboAuthUser.getUuid();
        val user = limboUserMapper.toUser(limboAuthUser);
            user.setToken(generateJwtService.generateToken(name, uuid));
        return user;
    }
}
