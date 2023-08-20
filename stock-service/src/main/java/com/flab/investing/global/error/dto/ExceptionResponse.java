package com.flab.investing.global.error.dto;

public record ExceptionResponse(
        String code,
        String message
) { }
