package com.flab.investing.stock.controller.response;

public record StockInfoResponse(
        String code,
        String message,
        Long stockId,
        String name,
        String corporationCode,
        String status,
        Integer amount,
        Integer stockHigh,
        Integer stockLower,
        Integer highLimit,
        Integer lowerLimit
) {
}
