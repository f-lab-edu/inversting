package com.flab.investing.stock.application;

import com.flab.investing.stock.domain.DailyStockStatistic;
import com.flab.investing.stock.domain.mappper.DailyStockMapper;
import com.flab.investing.stock.repository.DailyStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyStockService {

    private final DailyStockRepository dailyStockRepository;
    private final DailyStockMapper dailyStockMapper;

    public List<DailyStockStatistic> findDailyStockStatistics(final Long stockId, final LocalDate startDate, final LocalDate endDate) {
        return dailyStockRepository.findByStockIdAndCreatedAtBetween(stockId, startDate, endDate)
                .stream()
                .map(dailyStockMapper::toDailyStockStatistic)
                .collect(Collectors.toList());
    }

}
