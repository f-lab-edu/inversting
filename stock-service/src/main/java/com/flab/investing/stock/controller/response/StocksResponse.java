package com.flab.investing.stock.controller.response;

public record StocksResponse(
        Long id,
        String code,
        String name,
        Integer price
) {
}
