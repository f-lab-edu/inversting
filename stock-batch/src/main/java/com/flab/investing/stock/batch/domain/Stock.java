package com.flab.investing.stock.batch.domain;

public record Stock(
        Long id,
        String code,
        String shortCode,
        String name,
        String shortName,
        String corporationCode
) {
}