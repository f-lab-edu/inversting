package com.flab.investing.stock.domain;

import java.math.BigDecimal;

public record DailyStockStatistic(
        Long stockId,
        String code,
        Integer startAmount,
        Integer lastAmount,
        String sign,
        Integer stockHigh,
        Integer stockLower,
        Integer highLimit,
        Integer lowerLimit,
        Long totalTrading,
        BigDecimal totalAmount
) {



}
