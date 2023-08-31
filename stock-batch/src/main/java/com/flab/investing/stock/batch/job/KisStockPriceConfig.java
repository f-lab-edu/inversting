package com.flab.investing.stock.batch.job;

import com.flab.investing.stock.batch.step.KisPriceTokenStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KisStockPriceConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final KisPriceTokenStep kisPriceTokenStep;

    @Bean
    public Job kisStockPriceJob() {
        return jobBuilderFactory.get("kisStocksPriceJob")
                .incrementer(new RunIdIncrementer())
                .start(kisPriceTokenStep.kisTokenSearchStep())
                .next(kisPriceTokenStep.getKisPriceStep())
                .build();
    }

}
