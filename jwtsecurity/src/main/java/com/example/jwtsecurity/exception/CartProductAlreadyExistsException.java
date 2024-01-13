package com.example.jwtsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartProductAlreadyExistsException extends Exception {
    private String exception;
    public CartProductAlreadyExistsException(String exception){
        super(exception);
        this.exception=exception;
    }
    public CartProductAlreadyExistsException(){}

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
