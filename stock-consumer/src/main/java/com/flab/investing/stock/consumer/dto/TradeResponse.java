package com.flab.investing.stock.consumer.dto;

import com.flab.investing.stock.common.TradeCode;

public record TradeResponse(
        Long tradeId,
        Long stockId,
        Long userId,
        Integer stockOfNumber,
        Integer stockCount,
        TradeCode tradeCode
) {
}
