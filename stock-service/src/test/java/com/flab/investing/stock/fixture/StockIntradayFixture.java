package com.flab.investing.stock.fixture;

import com.flab.investing.stock.domain.entity.StockIntraday;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class StockIntradayFixture {

    public static StockIntraday create(Long stockId, Integer price) {
        return new StockIntraday(
                UUID.randomUUID().toString(),
                stockId,
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                "",
                String.valueOf(price),
                "",
                "",
                String.valueOf(price),
                String.valueOf(price),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}
