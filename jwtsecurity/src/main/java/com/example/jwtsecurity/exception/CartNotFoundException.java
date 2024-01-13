package com.example.jwtsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends Exception {
    private String exception;

    public CartNotFoundException(String exception) {
        super(exception);
        this.exception = exception;
    }

    public CartNotFoundException() {
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
