package com.flab.investing.global.error.exception;

public class InvalidJwtException extends RuntimeException{

    public InvalidJwtException(String message) {
        super(message);
    }
}
