package com.flab.investing.global.error.dto;

import lombok.Getter;

@Getter
public class ExceptionResponse {

    private final String code;
    private final String message;

    public ExceptionResponse(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
