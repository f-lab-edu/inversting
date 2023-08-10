package com.flab.investing.stock.batch.application;

import com.flab.investing.stock.batch.domain.Stock;
import com.flab.investing.stock.batch.domain.StockPrice;
import com.flab.investing.stock.batch.domain.Token;
import com.flab.investing.stock.batch.infrastructure.stock.StockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockMapper stockMapper;
    private final KisService kisService;

    public void save(List<Stock> stocks) {
        stockMapper.insert(stocks);
    }

    public void searchPrice(Stock stock, Token token) {
        StockPrice price = kisService.getPrice(token, stock.getCode());
    }

}
