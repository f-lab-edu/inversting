package com.flab.investing.stock.consumer.dto;

public record TradeException(
        Long tradeId,
        Long stockId,
        Long userId,
        String message
) {
}
