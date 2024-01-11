package com.example.spring_mysql_api.utilitys;

public class ApiResponse<T> {

    private final T data;
    private final String errorMessage;

    public ApiResponse(T data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}