package com.flab.investing.global.error.exception;

import static com.flab.investing.global.error.exception.constant.ExceptionMessage.NOT_MATCH_PASSWORD;

public class NotMatchPasswordException extends RuntimeException {

    public NotMatchPasswordException() {
        super(NOT_MATCH_PASSWORD.getMessage());
    }
}
