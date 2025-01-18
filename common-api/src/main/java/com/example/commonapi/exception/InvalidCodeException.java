package com.example.commonapi.exception;

public class InvalidCodeException extends RuntimeException{

    public InvalidCodeException(String message) {
        super(message);
    }
}
