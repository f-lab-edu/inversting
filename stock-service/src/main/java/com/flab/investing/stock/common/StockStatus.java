package com.flab.investing.stock.common;

import lombok.Getter;

@Getter
public enum StockStatus {

    ON_HOLD("대기중"),
    EXECUTION("체결"),
    ROOLBACK("롤백");

    private final String value;

    StockStatus(String value) {
        this.value = value;
    }

}
