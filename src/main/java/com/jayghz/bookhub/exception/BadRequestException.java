package com.jayghz.bookhub.exception;

public class BadRequestException extends RuntimeException {
   
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
    
}
