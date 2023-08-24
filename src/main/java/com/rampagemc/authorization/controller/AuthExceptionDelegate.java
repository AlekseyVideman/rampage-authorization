package com.rampagemc.authorization.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * A delegate used for handling of the {@link com.rampagemc.authorization.exception.UserNotRegistered} exception, that throwing when UserDetailsService can`t find requested user.
 * By default, UserDetailsService suppresses any RuntimeException so {@link WebExceptionHandler} can`t then handle these exceptions.
 */
@Component
@RequiredArgsConstructor
public class AuthExceptionDelegate implements AuthenticationEntryPoint {
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        if (authException instanceof InternalAuthenticationServiceException) {
            handlerExceptionResolver.resolveException(request, response, null, authException);
            return;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
