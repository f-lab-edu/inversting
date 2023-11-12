package com.flab.investing.stock.batch.domain;

import com.flab.investing.stock.batch.domain.entity.StockIntraday;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StockPriceMapper {

    public static StockIntraday mapper(final StockPrice stockPrice) {
        return new StockIntraday(
                stockPrice.stockId(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                stockPrice.code(),
                stockPrice.amount(),
                stockPrice.status(),
                stockPrice.sign(),
                stockPrice.highLimit(),
                stockPrice.lowerLimit()
        );
    }

}
