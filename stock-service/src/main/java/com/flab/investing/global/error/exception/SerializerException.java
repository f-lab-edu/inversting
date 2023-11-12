package com.flab.investing.global.error.exception;

public class SerializerException extends RuntimeException{

    private static final String MESSAGE = "데이터 변환 과정중에 에러가 발생하였습니다.";

    public SerializerException() {
        super(MESSAGE);
    }
}
