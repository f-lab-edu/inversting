package com.flab.investing.global.error.exception;

import static com.flab.investing.global.error.exception.constant.ExceptionMessage.ALREADY_EMAIL;

public class ExistsEmailException extends RuntimeException {

    public ExistsEmailException() {
        super(ALREADY_EMAIL.getMessage());
    }
}
