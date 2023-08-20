package com.flab.investing.stock.application.dto;

public record PurchaseRequest(
        Long stockId,
        String userName,
        Integer stockOfNumber,
        Integer stockCount
) {
}
