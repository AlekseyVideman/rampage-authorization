package com.rampagemc.authorization.controller;

import com.rampagemc.authorization.dto.UserDto;
import com.rampagemc.authorization.model.service.DomainUserService;
import com.rampagemc.authorization.model.service.JwtTokenService;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequestMapping("/web")
@RestController
public class RegAuthController {
    @Autowired
    private DomainUserService userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(RegistrationRequest reg) {
        userService.registerUser(new UserDto(reg.getUsername(), reg.getPassword()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(UserDetailsService userDetailsService, AuthenticatedPrincipal principal) {
        var userDetails = userDetailsService.loadUserByUsername(principal.getName());
        System.out.println("Я ЗДЕСЬ ААААА УУУУУ /AUTH");
        // TODO я не уверен в какой отдаёт getPassword, сырой или хешированный.
        var token = jwtTokenService.generateToken(userDetails.getUsername(), userDetails.getPassword());
        var f = new AuthResponse(userDetails.getUsername(), token.token());
        System.out.println("ВОТ ЭТО Я ДОЛЖЕН БЫЛ УВИДЕТЬ!!!  " + Arrays.toString(new JacksonSerializer<AuthResponse>().serialize(f)));
        return ResponseEntity.ok(f);
    }
}
