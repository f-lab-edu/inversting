package com.flab.investing.global.common;

import java.time.YearMonth;

public final class YearDateParser {

    public static YearMonth getYearMonth(final String yearMonthString) {
        int year = Integer.parseInt(yearMonthString.substring(0, 4));
        int month = Integer.parseInt(yearMonthString.substring(4));

        return YearMonth.of(year, month);
    }

}
