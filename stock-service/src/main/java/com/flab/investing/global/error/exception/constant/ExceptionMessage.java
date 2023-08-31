package com.flab.investing.global.error.exception.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionMessage {

    NOT_FOUND_STOCK("주식을 찾을 수 없습니다.");

    private final String message;

}
