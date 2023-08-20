package com.flab.investing.global.error.exception;

import com.flab.investing.global.error.constant.ExceptionCode;
import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {

    private String code;
    private String message;

    public AuthenticationException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
