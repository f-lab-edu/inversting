package com.flab.investing.global.error.exception;

import static com.flab.investing.global.error.exception.constant.ExceptionMessage.INVALID_TOKEN;

public class InvalidJwtException extends RuntimeException{

    public InvalidJwtException() {
        super(INVALID_TOKEN.getMessage());
    }
}
