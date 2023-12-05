package com.flab.investing.stock.controller.response;

public record StockPurchaseResponse(
        String code,
        String message,
        Long stockId,
        Integer totalAmount,
        Integer stockOfAmount,
        Integer stockCount
) {
}
