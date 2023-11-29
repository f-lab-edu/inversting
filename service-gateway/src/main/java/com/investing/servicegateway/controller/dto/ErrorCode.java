package com.investing.servicegateway.controller.dto;

public enum ErrorCode {

    FALLBACK("9999", "서버에 연결에 실패하였습니다.");

    private final String code;
    private final String message;

    ErrorCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
