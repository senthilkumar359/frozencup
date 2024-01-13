package com.example.jwtsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleproductNotFoundException(ProductNotFoundException exception, WebRequest request){
        UserErrorDetails error=new UserErrorDetails( exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<?> handleCartNotFoundException(CartNotFoundException exception,WebRequest request){
        UserErrorDetails error=new UserErrorDetails(exception.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartProductAlreadyExistsException.class)
    public ResponseEntity<?> handleCartProductExistsException(CartProductAlreadyExistsException exception,WebRequest request){
        UserErrorDetails error=new UserErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
