package com.flab.investing.stock.application;

import com.flab.investing.global.error.exception.NotFoundStockException;
import com.flab.investing.stock.domain.entity.Stock;
import com.flab.investing.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public List<Stock> findAllPageable(final Pageable pageable) {
        return stockRepository.findAll(pageable).getContent();
    }

    public Stock findByStockId(final Long stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new NotFoundStockException());
    }

}
