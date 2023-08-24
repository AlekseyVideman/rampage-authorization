package com.rampagemc.authorization.controller;

import com.rampagemc.authorization.dto.response.ErrorDto;
import com.rampagemc.authorization.exception.UserAlreadyRegistered;
import com.rampagemc.authorization.exception.UserNotRegistered;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotRegistered.class)
    public ErrorDto notRegistered(Exception e) {
        return new ErrorDto(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserAlreadyRegistered.class)
    public ErrorDto alreadyRegistered(Exception e) {
        return new ErrorDto(e.getMessage());
    }
}
