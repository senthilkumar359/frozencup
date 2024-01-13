package com.example.jwtsecurity.exception;

public class NoOrdersFoundException extends Exception {
    public NoOrdersFoundException(String description){
        super(description);
    }
}
