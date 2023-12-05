package com.flab.investing.stock.common;

import lombok.Getter;

@Getter
public enum DivisionStatus {

    BUY("구매"),
    SELL("판매");

    private final String value;

    DivisionStatus(String value) {
        this.value = value;
    }
}
