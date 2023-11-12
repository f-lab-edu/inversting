package com.flab.investing.global.error.exception;

public class AwsSqsRetryException extends RuntimeException{

    private static final String MESSAGE = "SQS 전송시, 에러가 발생하였습니다.";

    public AwsSqsRetryException() {
        super(MESSAGE);
    }
}
