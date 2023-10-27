package com.flab.investing.stock.batch.infrastructure.kis.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum KisProperties {

    GRANT_TYPE("client_credentials"),
    MARKET_CODE("J"),
    BEARER("BEARER "),
    TRACE_ID("FHKST01010100");

    private final String value;

}
