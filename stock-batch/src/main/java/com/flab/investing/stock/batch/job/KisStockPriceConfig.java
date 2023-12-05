package com.flab.investing.stock.batch.job;

import com.flab.investing.stock.batch.step.KisPriceTokenStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KisStockPriceConfig {

    private final KisPriceTokenStep kisPriceTokenStep;
    private final JobRepository jobRepository;

    @Bean
    public Job kisStockPriceJob() {
        return new JobBuilder("kisStockPriceJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(kisPriceTokenStep.kisTokenSearchStep())
                .next(kisPriceTokenStep.getKisPriceStep())
                .build();
    }

}
