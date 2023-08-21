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
public class KisStockPriceJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final KisPriceTokenStep kisPriceTokenStep;

    @Bean
    public Job kisStockPriceJob() {
        return jobBuilderFactory.get("kisStockPriceJob")
                .incrementer(new RunIdIncrementer())
                .start(kisPriceTokenStep.kisPriceTokenStep())
                .build();
    }

}
