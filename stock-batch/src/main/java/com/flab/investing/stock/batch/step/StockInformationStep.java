package com.flab.investing.stock.batch.step;

import com.flab.investing.stock.batch.step.tasklet.StockInformationTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StockInformationStep {

    private final PlatformTransactionManager transactionManager;

    private final StepBuilderFactory stepBuilderFactory;
    private final StockInformationTasklet stockInformationTasklet;

    @Bean
    public Step stcokInformationStep() {
        return stepBuilderFactory.get("stcokInformationStep")
                .tasklet(stockInformationTasklet)
                .transactionManager(transactionManager)
                .build();
    }

}
