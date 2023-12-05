package com.flab.investing.stock.controller.response;

import java.util.List;

public record StockListInfo(
        String code,
        String message,
        List<StocksResponse> data
) {
}
