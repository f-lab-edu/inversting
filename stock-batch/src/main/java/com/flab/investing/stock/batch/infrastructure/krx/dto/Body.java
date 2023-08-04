package com.flab.investing.stock.batch.infrastructure.krx.dto;

public record Body(
        int numOfRows,
        int pageNo,
        int totalCount,
        Items items) {}
