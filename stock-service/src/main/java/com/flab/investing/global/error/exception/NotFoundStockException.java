package com.flab.investing.global.error.exception;

import static com.flab.investing.global.error.exception.constant.ExceptionMessage.NOT_FOUND_STOCK;

public class NotFoundStockException extends RuntimeException {

    public NotFoundStockException() {
        super(NOT_FOUND_STOCK.getMessage());
    }
}
