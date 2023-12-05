package com.flab.investing.stock.batch.domain;

import java.math.BigDecimal;

public record Stock(
        Long id,
        String code,
        String shortCode,
        String name,
        String shortName,
        String corporationCode,
        BigDecimal price
) {
}