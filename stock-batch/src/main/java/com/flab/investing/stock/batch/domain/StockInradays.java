package com.flab.investing.stock.batch.domain;

import com.flab.investing.stock.batch.domain.entity.StockIntraday;

import java.util.List;

public record StockInradays(
        List<StockIntraday> stockIntradays
) {

    public Integer getStartAmount() {
        return Integer.parseInt(stockIntradays.get(0).getAmount());
    }

    public Integer getLastAmount() {
        return Integer.parseInt(stockIntradays.get(stockIntradays.size()-1).getAmount());
    }

    public String getSign() {
        return stockIntradays.get(stockIntradays.size()-1).getSign();
    }

    public Integer getHighLimit() {
        return stockIntradays.stream()
                .mapToInt(stockIntraday -> Integer.parseInt(stockIntraday.getHighLimit()))
                .max()
                .orElse(0);
    }

    public Integer getLowerLimit() {
        return stockIntradays.stream()
                .mapToInt(stockIntraday -> Integer.parseInt(stockIntraday.getLowerLimit()))
                .min()
                .orElse(0);
    }

}
