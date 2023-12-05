package com.flab.investing.stock.controller.response;

import java.math.BigDecimal;
import java.util.List;

public record DailyStatisticsResponse(

        String code,
        String message,
        String searchDate,
        Integer count,
        List<DailyStatisticsData> data
) {

    public record DailyStatisticsData(
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

}


