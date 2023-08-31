package com.flab.investing.stock.controller.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponseCode {

    SUCCESS("0000", "정상 체결되었습니다.");

    private final String code;
    private final String message;

}
