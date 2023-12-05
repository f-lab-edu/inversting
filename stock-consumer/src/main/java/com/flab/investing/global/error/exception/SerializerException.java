package com.flab.investing.global.error.exception;

public class SerializerException extends RuntimeException{

    private static final String FORMAT = "파싱 과정중 에러가 발생하였습니다. : %s";

    public SerializerException(final String message) {
        super(String.format(FORMAT, message));
    }
}
