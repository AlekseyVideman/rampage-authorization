package com.rampagemc.authorization.controller;

import com.rampagemc.authorization.dto.request.UserDto;
import com.rampagemc.authorization.dto.response.AuthResponseDto;
import com.rampagemc.authorization.service.RegisterUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/web")
@RequiredArgsConstructor
public class RegAuthController {

    private final RegisterUserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/register")
    public void register(UserDto userDto) {
        userService.registerUser(userDto);
    }

    @PostMapping("/auth")
    public AuthResponseDto auth(Principal principal) {
        var user = userService.getRegisteredUser(principal.getName());
        return new AuthResponseDto(user.getUsername(), user.getToken().token());
    }
}

