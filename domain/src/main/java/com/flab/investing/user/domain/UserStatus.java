package com.flab.investing.user.domain;

public enum UserStatus {
    ACTIVE,     //  정상
    INACTIVE,   //  장기 미접속자
    NOT_IDENTITY, // 인증전
    WITHDRAWAL; //  해지
}
