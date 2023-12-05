package com.flab.investing.stock.fixture;

import com.flab.investing.stock.domain.entity.Stock;

import java.util.Random;

public class StockFixture {

    private static final Random random = new Random();

    public static Stock create(String companyName, int price) {
        String code = String.valueOf(random.nextInt(999999));

        return new Stock(code, code, companyName, companyName, code, price, price, price, price, price);
    }

}
