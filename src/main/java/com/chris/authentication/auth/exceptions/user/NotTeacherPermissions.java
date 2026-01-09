package com.chris.authentication.auth.exceptions.user;

public class NotTeacherPermissions extends RuntimeException {
    public NotTeacherPermissions(String message) {
        super(message);
    }
}
