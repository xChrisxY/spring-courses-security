package com.chris.authentication.auth.controllers;

import com.chris.authentication.auth.entities.Error;
import com.chris.authentication.auth.exceptions.courses.CourseNotFoundException;
import com.chris.authentication.auth.exceptions.lessons.LessonNotFoundException;
import com.chris.authentication.auth.exceptions.user.UserNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error<Map<String, Object>>> methodArgumentNotValid(MethodArgumentNotValidException e){

        Map<String, Object> errors = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        fieldErrors.forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });

        Error error = new Error(
                "Error de validación en el cuerpo de la petición",
                errors,
                HttpStatus.FORBIDDEN.value(),
                new Date()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(error);
    }

    @ExceptionHandler({CourseNotFoundException.class, UserNotFoundException.class, LessonNotFoundException.class})
    public ResponseEntity<Error> entityNotFound(Exception e) {

        Error error = new Error(
                "Entidad no encontrada.",
                e.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                new Date()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(error);
    }

}
