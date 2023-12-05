package com.flab.investing.stock.integration.application;

import com.flab.investing.BatchTestSupport;
import com.flab.investing.stock.batch.application.StockIntradayService;
import com.flab.investing.stock.batch.domain.StockPrice;
import com.flab.investing.stock.batch.domain.StockPriceMapper;
import com.flab.investing.stock.batch.domain.entity.StockIntraday;
import com.flab.investing.stock.batch.domain.repository.StockIntradayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class StockIntradayServiceTest extends BatchTestSupport {

    private final StockIntradayService stockIntradayService;
    private final StockIntradayRepository stockIntradayRepository;

    private StockPrice stockPrice;

    public StockIntradayServiceTest(final StockIntradayService stockIntradayService,
                                    final StockIntradayRepository stockIntradayRepository) {
        this.stockIntradayService = stockIntradayService;
        this.stockIntradayRepository = stockIntradayRepository;
    }

    @BeforeEach
    void setUp() {
        this.stockIntradayRepository.deleteAll();
        this.stockPrice = new StockPrice(
                0L,
                "A000120",
                "76100",
                "55",
                "2",
                "3188276500",
                "42010",
                "76400",
                "75000",
                "98000",
                "52800"
        );
    }

    @Test
    @DisplayName("5분마다 저장될 데이터 몽고 DB로 저장하기")
    void intraStockSave() {
        List<StockIntraday> stockIntradays = List.of(StockPriceMapper.mapper(stockPrice));

        stockIntradayService.saveAll(stockIntradays);

        assertThat(stockIntradayRepository.findAll()).hasSize(1);
    }

}
