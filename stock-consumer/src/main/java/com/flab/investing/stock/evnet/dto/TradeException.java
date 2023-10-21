package com.flab.investing.stock.evnet.dto;

public record TradeException(
        Long tradeId,
        Long stockId,
        Long userId,
        String message
) {
}
