package com.flab.investing.global.error.exception.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionMessage {

    EXPIRED_TOKEN("유효시간이 지난 토큰을 사용하였습니다."),
    ALREADY_EMAIL("이미 등록된 이메일이 존재합니다."),
    INVALID_TOKEN("유효하지 않은 토큰을 사용하였습니다."),
    NOTFOUND_USER("유저아이디를 찾을 수 없습니다."),
    NOT_MATCH_PASSWORD("패스워드가 일치 하지 않습니다.");

    private final String message;

}
