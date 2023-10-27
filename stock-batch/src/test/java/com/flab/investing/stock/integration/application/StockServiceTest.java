package com.flab.investing.stock.integration.application;

import com.flab.investing.BatchTestSupport;
import com.flab.investing.stock.batch.application.StockService;
import com.flab.investing.stock.batch.domain.Stock;
import com.flab.investing.stock.batch.infrastructure.stock.StockMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StockServiceTest extends BatchTestSupport {

    private final StockService stockService;
    private final StockMapper stockMapper;

    public StockServiceTest(final StockService stockService,
                            final StockMapper stockMapper) {
        this.stockService = stockService;
        this.stockMapper = stockMapper;
    }

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock(null,
                "KR7000120006",
                "A000120",
                "씨제이대한통운(주)",
                "CJ대한통운",
                "1101110006167",
                BigDecimal.ZERO);
    }

    @Test
    @DisplayName("주식 정보를 저장한다.")
    void save() {
        stockService.save(Collections.singletonList(stock));

        List<Stock> stocks = stockMapper.findAll();

        assertThat(stock.code()).isEqualTo(stocks.get(0).code());
    }

}
