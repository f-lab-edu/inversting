package com.flab.investing.stock.integration.job;

import com.flab.investing.BatchTestSupport;
import com.flab.investing.stock.batch.application.KisService;
import com.flab.investing.stock.batch.application.StockIntradayService;
import com.flab.investing.stock.batch.application.StockService;
import com.flab.investing.stock.batch.domain.Stock;
import com.flab.investing.stock.batch.domain.StockPrice;
import com.flab.investing.stock.batch.domain.Token;
import com.flab.investing.stock.batch.domain.repository.StockIntradayRepository;
import com.flab.investing.stock.batch.infrastructure.kis.KisTokenRepository;
import com.flab.investing.stock.batch.infrastructure.stock.StockMapper;
import com.flab.investing.stock.batch.job.KisStockPriceConfig;
import com.flab.investing.stock.batch.step.KisPriceTokenStep;
import com.flab.investing.stock.global.config.KisAccessConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
        KisStockPriceConfig.class,
        KisPriceTokenStep.class,
        StockService.class,
        KisService.class,
        KisTokenRepository.class,
        KisAccessConfig.class,
        StockIntradayService.class,
        StockIntradayRepository.class
})
public class KisStockPriceConfigTest extends BatchTestSupport {

    @MockBean
    private KisService kisService;

    private StockPrice stockPrice;

    private final JobLauncherTestUtils jobLauncherTestUtils;
    private final StockMapper stockMapper;
    private final StockIntradayRepository stockIntradayRepository;

    public KisStockPriceConfigTest(final JobLauncherTestUtils jobLauncherTestUtils,
                                   final StockMapper stockMapper,
                                   final StockIntradayRepository stockIntradayRepository) {
        this.jobLauncherTestUtils = jobLauncherTestUtils;
        this.stockMapper = stockMapper;
        this.stockIntradayRepository = stockIntradayRepository;
    }

    @BeforeEach
    void setUp() {
        stockIntradayRepository.deleteAll();
        stockMapper.insert(Collections.singletonList(new Stock(
                null,
                "KR7000120006",
                "A000120",
                "씨제이대한통운(주)",
                "CJ대한통운",
                "1101110006167",
                BigDecimal.ZERO
        )));

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
    void kisStockPriceJob() throws Exception {
        given(kisService.getPrice(any(), any())).willReturn(stockPrice);
        given(kisService.getToken()).willReturn(new Token("test Token"));

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);

        Integer amount = Integer.parseInt(stockIntradayRepository.findAll().get(0).getAmount());

        assertThat(amount).isEqualTo(76100);
    }
}
