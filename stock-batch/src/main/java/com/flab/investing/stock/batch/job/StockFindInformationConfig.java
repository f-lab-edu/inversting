package com.flab.investing.stock.batch.job;

import com.flab.investing.stock.batch.job.listener.StockInformationJobListener;
import com.flab.investing.stock.batch.step.StockInformationStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StockFindInformationConfig {

    private final StockInformationJobListener stockInformationJobListener;

    private final StockInformationStep stockInformationStep;
    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job stockFindInformationJob() {
        return jobBuilderFactory.get("stockFindInformationJob")
                .incrementer(new RunIdIncrementer())
                .listener(stockInformationJobListener)
                .start(stockInformationStep.stcokInformationStep())
                .build();
    }

}
