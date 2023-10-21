package com.flab.investing.global.error.exception;

import static com.flab.investing.global.error.exception.constant.ExceptionMessage.NOT_FOUND_TRADE;

public class NotFoundTradeException extends RuntimeException{
    public NotFoundTradeException() {
        super(NOT_FOUND_TRADE.getMessage());
    }
}
