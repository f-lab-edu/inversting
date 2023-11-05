package com.flab.investing.stock.integration.job;

import com.flab.investing.BatchTestSupport;
import com.flab.investing.stock.batch.application.DailyStockService;
import com.flab.investing.stock.batch.application.StockIntradayService;
import com.flab.investing.stock.batch.domain.repository.DailyStockRepository;
import com.flab.investing.stock.batch.domain.repository.StockIntradayRepository;
import com.flab.investing.stock.batch.job.DailyStockStatisticsConfig;
import com.flab.investing.stock.batch.step.DailyStocksStatisticsStep;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
        DailyStockStatisticsConfig.class,
        DailyStocksStatisticsStep.class,
        StockIntradayService.class,
        DailyStockService.class,
        StockIntradayRepository.class,
        DailyStockRepository.class
})
public class DailyStockStatisticsConfigTest extends BatchTestSupport {

    private final JobLauncherTestUtils jobLauncherTestUtils;
    private final DailyStockRepository dailyStockRepository;

    public DailyStockStatisticsConfigTest(final JobLauncherTestUtils jobLauncherTestUtils,
                                          final DailyStockRepository dailyStockRepository) {
        this.jobLauncherTestUtils = jobLauncherTestUtils;
        this.dailyStockRepository = dailyStockRepository;
    }

    @Test
    @DisplayName("일별 주식 변동 상황을 기준으로 하루 통계 테이블에 넣는다.")
    void dailyStatistics() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }
}
