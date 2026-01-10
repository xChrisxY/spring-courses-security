package com.chris.authentication.auth.dto;

public class ApiResponse<T> {

    private boolean success = true;
    private String message;
    private int status = 200;
    private T data;

    public ApiResponse(){}

    public ApiResponse(boolean success, String message, int status, T data) {
        this.success = success;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ApiResponse(String message, T data) {
        this.success = true;
        this.message = message;
        this.status = 200;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
