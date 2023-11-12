package com.flab.investing.stock.batch.domain;

import java.math.BigDecimal;

public record DailyTrade(
        Long stockId,
        String code,
        String shortCode,
        String name,
        String shortName,
        Integer stockHigh,
        Integer stockLower,
        Long totalTrading,
        BigDecimal totalAmount
) {
}
