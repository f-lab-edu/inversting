package com.flab.investing.stock.batch.application;

import com.flab.investing.stock.batch.domain.DailyTrade;
import com.flab.investing.stock.batch.domain.StockInradays;
import com.flab.investing.stock.batch.domain.entity.StockIntraday;
import com.flab.investing.stock.batch.domain.repository.StockIntradayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockIntradayService {

    private final StockIntradayRepository stockIntradayRepository;

    @Transactional
    public void saveAll(final List<StockIntraday> stockIntradaies) {
        stockIntradayRepository.saveAll(stockIntradaies);
    }


    public StockInradays findByStockId(final DailyTrade dailyTrade, final String date) {
        List<StockIntraday> stockIntradays = stockIntradayRepository.findByStockIdAndDate(dailyTrade.stockId(), date);

        return new StockInradays(stockIntradays);
    }

}
