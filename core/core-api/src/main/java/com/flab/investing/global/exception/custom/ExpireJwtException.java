package com.flab.investing.global.exception.custom;

public class ExpireJwtException extends RuntimeException{

    public ExpireJwtException() {
        super("유효시간이 지난 토큰을 사용하였습니다.");
    }
}
