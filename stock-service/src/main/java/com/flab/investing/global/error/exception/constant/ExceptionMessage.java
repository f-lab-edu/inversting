package com.flab.investing.global.error.exception.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionMessage {

    NOT_FOUND_STOCK("주식을 찾을 수 없습니다."),
    NOT_FOUND_TRADE("거래정보를 찾을 수 없습니다."),
    INVALID_PARAMETER("유효하지 않은 파라미터 입니다.");

    private final String message;

}
