package com.flab.investing.global.error.constant;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    BAD_REQUEST("E001", "유효성 검사 실패"),
    CUSTOM_EXCEPTION("E002", "사용자 에러");

    private final String code;
    private final String description;

    ExceptionCode(final String code, final String description) {
        this.code = code;
        this.description = description;
    }
}
