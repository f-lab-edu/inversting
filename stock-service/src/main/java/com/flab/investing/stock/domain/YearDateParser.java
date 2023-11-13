package com.flab.investing.stock.domain;

import java.time.YearMonth;

public record YearDateParser(
        String yearMonthString
) {

    public YearMonth getYearMonth() {
        int year = Integer.parseInt(yearMonthString.substring(0, 4));
        int month = Integer.parseInt(yearMonthString.substring(4));

        return YearMonth.of(year, month);
    }
}
