package com.flab.investing.stock.batch.step;

import com.flab.investing.stock.batch.step.tasklet.StockInformationTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StockInformationStep {

    private final StockInformationTasklet stockInformationTasklet;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step stcokInformationStep() {
        return new StepBuilder("stockInformationStep", jobRepository)
                .tasklet(stockInformationTasklet, transactionManager)
                .build();
    }

}
