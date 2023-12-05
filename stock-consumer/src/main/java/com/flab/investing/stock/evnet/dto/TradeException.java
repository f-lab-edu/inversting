package com.flab.investing.stock.evnet.dto;

public record TradeException(
        String id,
        Long tradeId,
        Long stockId,
        Long userId,
        String message
) {
}
