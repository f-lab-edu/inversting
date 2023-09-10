package com.flab.investing.stock.common;

public enum TradeCode {

    BUY("매수"),
    SELL("매도");

    private final String value;

    TradeCode(String value) {
        this.value = value;
    }

}
