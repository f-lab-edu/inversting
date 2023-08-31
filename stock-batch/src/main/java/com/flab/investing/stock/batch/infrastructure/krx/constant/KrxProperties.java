package com.flab.investing.stock.batch.infrastructure.krx.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum KrxProperties {

    SEARCH_COUNT(10_000),
    DEFAULT_PAGE_NO(1);

    private final int value;

}
