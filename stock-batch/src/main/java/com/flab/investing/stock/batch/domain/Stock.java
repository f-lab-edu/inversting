package com.flab.investing.stock.batch.domain;

import lombok.Getter;

@Getter
public class Stock {

    private Long id;
    private final String code;
    private final String shortCode;
    private final String name;
    private final String shortName;
    private final String corporationCode;

    public Stock(Long id, String code, String shortCode, String name, String shortName, String corporationCode) {
        this.id = id;
        this.code = code;
        this.shortCode = shortCode;
        this.name = name;
        this.shortName = shortName;
        this.corporationCode = corporationCode;
    }
}
