package com.flab.investing.global.exception.dto;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    BAD_REQUEST("E001", "유효성 검사 실패"),
    CUSTOM_EXCEPTION("E002", "사용자 에러");

    private final String code;
    private final String description;

    ExceptionCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
