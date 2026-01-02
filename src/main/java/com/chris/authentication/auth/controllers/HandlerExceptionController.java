package com.chris.authentication.auth.controllers;

import com.chris.authentication.auth.entities.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> methodArgumentNotValid(MethodArgumentNotValidException e){

        Map<String, String> errors = new HashMap<>();

    }

}
