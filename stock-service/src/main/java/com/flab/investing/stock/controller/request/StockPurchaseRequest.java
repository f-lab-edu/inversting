package com.flab.investing.stock.controller.request;

public record StockPurchaseRequest(
        Long stockId,
        Integer stockOfAmount,
        Integer stockCount
) { }
