package com.flab.investing.global.error.exception;

public class SerializerException extends RuntimeException{

    private static final String MESSAGE_FORMAT = "데이터 변환중에 에러가 발생하였습니다. message: {}";

    public SerializerException(final String message) {
        super(String.format(MESSAGE_FORMAT, message));
    }
}
