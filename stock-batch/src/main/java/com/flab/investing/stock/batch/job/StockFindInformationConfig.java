package com.flab.investing.stock.batch.job;

import com.flab.investing.stock.batch.job.listener.StockInformationJobListener;
import com.flab.investing.stock.batch.step.StockInformationStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StockFindInformationConfig {

    private final StockInformationJobListener stockInformationJobListener;

    private final StockInformationStep stockInformationStep;
    private final JobRepository jobRepository;

    @Bean
    public Job stockFindInformationJob() {
        return new JobBuilder("stockFindInformationJob", jobRepository)
                .listener(stockInformationJobListener)
                .start(stockInformationStep.stcokInformationStep())
                .build();
    }

}
