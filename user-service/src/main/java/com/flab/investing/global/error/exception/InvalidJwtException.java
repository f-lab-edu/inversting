package com.flab.investing.global.error.exception;

public class InvalidJwtException extends RuntimeException{

    public InvalidJwtException() {
        super("유효하지 않은 토큰을 사용하였습니다.");
    }
}
