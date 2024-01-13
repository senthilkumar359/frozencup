package com.example.jwtsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception {
    private String error;

    public ProductNotFoundException(String exception) {
        super(exception);
        error = exception;
    }

    public ProductNotFoundException() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
