package com.flab.investing.global.exception.custom;

public class ExistsEmailException extends RuntimeException {

    public ExistsEmailException() {
        super("이미 등록된 이메일이 존재합니다.");
    }
}