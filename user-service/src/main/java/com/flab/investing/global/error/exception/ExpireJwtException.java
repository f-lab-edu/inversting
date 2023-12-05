package com.flab.investing.global.error.exception;

import static com.flab.investing.global.error.exception.constant.ExceptionMessage.EXPIRED_TOKEN;

public class ExpireJwtException extends RuntimeException{

    public ExpireJwtException() {
        super(EXPIRED_TOKEN.getMessage());
    }
}
