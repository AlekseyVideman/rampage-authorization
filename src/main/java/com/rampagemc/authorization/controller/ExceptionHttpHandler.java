package com.rampagemc.authorization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHttpHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler()
    public ResponseEntity<String> handler(Exception e) {
        return ResponseEntity.accepted().body("ОШИБКА:  " + e);
    }
}
