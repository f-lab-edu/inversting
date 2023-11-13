package com.flab.investing.stock.controller;

import com.flab.investing.global.common.YearDateParser;
import com.flab.investing.stock.application.DailyStockService;
import com.flab.investing.stock.controller.request.DailyStatisticsRequest;
import com.flab.investing.stock.controller.response.DailyStatisticsResponse;
import com.flab.investing.stock.controller.response.ResponseCode;
import com.flab.investing.stock.domain.mappper.DailyStockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class StockStatisticController {

    private final DailyStockService dailyStockService;
    private final DailyStockMapper dailyStockMapper;

    @GetMapping("/statistic/daily")
    public ResponseEntity<DailyStatisticsResponse> dailyStatics(DailyStatisticsRequest request) {
        final YearMonth yearMonth = YearDateParser.getYearMonth(request.searchDate());
        final LocalDate startDate = yearMonth.atDay(1);
        final LocalDate endDate = yearMonth.atEndOfMonth();

        List<DailyStatisticsResponse.DailyStatisticsData> DailyStatisticsData = dailyStockService.findDailyStockStatistics(request.stockId(), startDate, endDate).stream()
                .map(dailyStockMapper::toDailyStatisticsResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new DailyStatisticsResponse(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMessage(),
                request.searchDate(),
                DailyStatisticsData.size(),
                DailyStatisticsData
        ));
    }

}
