package com.flab.investing.global.error.exception;

import com.flab.investing.global.error.constant.ExceptionCode;

public class InvalidJwtException extends AuthenticationException {

    public InvalidJwtException() {
        super(ExceptionCode.INVALID_TOKEN.getCode(),
                ExceptionCode.INVALID_TOKEN.getDescription());
    }
}
