package com.flab.investing.stock.application;

import com.flab.investing.global.error.exception.NotFoundStockException;
import com.flab.investing.stock.domain.entity.StockIntraday;
import com.flab.investing.stock.repository.StockIntradayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockIntraDayService {

    private final StockIntradayRepository stockIntradayRepository;

    public StockIntraday findByStockId(final Long stockId) {
        return stockIntradayRepository.findTopByStockIdOrderByCreatedAtDesc(stockId)
                .orElseThrow(NotFoundStockException::new);
    }

}
