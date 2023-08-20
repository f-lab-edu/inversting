package com.flab.investing.global.error.constant;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    SUCCESS("0000", "성공하였습니다."),
    BAD_REQUEST("E001", "유효성 검사 실패"),
    CUSTOM_EXCEPTION("E002", "사용자 에러"),
    INVALID_TOKEN("E003", "유효하지 않은 토큰입니다."),
    NOT_FOUND_SESSION("E004", "로그인 후, 다시 시도해 주세요");

    private final String code;
    private final String description;

    ExceptionCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
