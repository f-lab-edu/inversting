package com.flab.investing.global.exception.custom;

public class NotMatchPasswordException extends RuntimeException {

    public NotMatchPasswordException() {
        super("패스워드가 일치 하지 않습니다.");
    }
}
