package com.flab.investing.stock.application.dto;

import com.flab.investing.stock.common.DivisionStatus;

public record TradeData(
    Long stockId,
    Long userId,
    int count,
    int price,
    DivisionStatus divisionStatus
) {
}
