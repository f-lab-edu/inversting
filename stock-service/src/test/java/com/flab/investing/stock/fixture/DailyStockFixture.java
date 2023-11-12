package com.flab.investing.stock.fixture;

import com.flab.investing.stock.domain.entity.DailyStock;

import java.math.BigDecimal;
import java.util.Random;

public class DailyStockFixture {

    private static final Random random = new Random();

    public static DailyStock create(final Long stockId,
                                    final String date) {
        String code = String.valueOf(random.nextInt(999999));

        return new DailyStock(
                stockId,
                code,
                date,
                1000,
                1000,
                "+23",
                1500,
                500,
                1500,
                500,
                10000L,
                BigDecimal.valueOf(100000)
        );
    }

}
