package com.chris.authentication.auth.entities;

import java.util.Date;
import java.util.Map;

public class Error {

    private String message;
    private Map<String, Object> errors;
    private int status;
    private Date date;

    public Error(){}

    public Error(String message, Map<String, Object> errors, int status, Date date) {
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

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
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
