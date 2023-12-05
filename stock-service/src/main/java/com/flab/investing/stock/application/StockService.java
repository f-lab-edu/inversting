package com.flab.investing.stock.application;

import com.flab.investing.global.error.exception.NotFoundStockException;
import com.flab.investing.stock.domain.entity.Stock;
import com.flab.investing.stock.domain.entity.StockIntraday;
import com.flab.investing.stock.repository.StockIntradayRepository;
import com.flab.investing.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional(readOnly = true)
    public List<Stock> findAllPageable(final Pageable pageable) {
        return stockRepository.findAll(pageable).getContent();
    }

    public Stock findByStockId(final Long stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(NotFoundStockException::new);
    }

}
