package com.chris.authentication.auth.entities;

import java.util.Date;
import java.util.Map;

public class Error<T> {

    private String message;
    private T errors;
    private int status;
    private Date date;

    public Error(){}

    public Error(String message, T errors, int status, Date date) {
        this.message = message;
        this.errors = errors;
        this.status = status;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getErrors() {
        return errors;
    }

    public void setErrors(T errors) {
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
