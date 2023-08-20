package com.flab.investing.stock.application;

import com.flab.investing.stock.controller.response.StockInfoResponse;
import com.flab.investing.stock.controller.response.StocksResponse;
import com.flab.investing.stock.repository.StockCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockCustomRepository stockRepository;

    public List<StocksResponse> findAllPageable(Pageable pageable) {
        return stockRepository.findAllByPageable(pageable);
    }

    public StockInfoResponse findByStockId(Long stockId) {
        return stockRepository.findByStockId(stockId);
    }

}
