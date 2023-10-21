package com.flab.investing.global.error.exception;

public class RabbitMqRetryException extends RuntimeException{

    public RabbitMqRetryException(final String message) {
        super(message);
    }
}
