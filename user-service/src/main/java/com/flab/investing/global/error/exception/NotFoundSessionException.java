package com.flab.investing.global.error.exception;

import com.flab.investing.global.error.constant.ExceptionCode;

public class NotFoundSessionException extends AuthenticationException {

    public NotFoundSessionException() {
        super(ExceptionCode.NOT_FOUND_SESSION.getCode(),
                ExceptionCode.NOT_FOUND_SESSION.getDescription());
    }
}
