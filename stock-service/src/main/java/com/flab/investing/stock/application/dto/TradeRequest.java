package com.flab.investing.stock.application.dto;

import com.flab.investing.stock.common.TradeCode;

public record TradeRequest(
        Long tradeId,
        Long stockId,
        Long userId,
        Integer stockOfNumber,
        Integer stockCount,
        TradeCode tradeCode
) {
}
