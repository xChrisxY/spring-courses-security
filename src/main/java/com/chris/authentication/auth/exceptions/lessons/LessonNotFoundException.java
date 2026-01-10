package com.chris.authentication.auth.exceptions.lessons;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(String message) {
        super(message);
    }
}
