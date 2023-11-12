package com.flab.investing.stock.batch.job;

import com.flab.investing.stock.batch.step.DailyStocksStatisticsStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DailyStockStatisticsConfig {

    private final DailyStocksStatisticsStep dailyStocksStatisticsStep;
    private final JobRepository jobRepository;

    @Bean
    public Job dailyStockStatisticsJob() {
        return new JobBuilder("dailyStockStatisticsJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(dailyStocksStatisticsStep.dailyStocksStatistics())
                .build();
    }

}
