package com.flab.investing.stock.controller.request;

public record StockSellRequest(
        Long stockId,
        Integer stockOfAmount,
        Integer stockCount
){ }
