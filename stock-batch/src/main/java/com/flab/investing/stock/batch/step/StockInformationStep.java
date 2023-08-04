package com.flab.investing.stock.batch.step;

import com.flab.investing.stock.batch.infrastructure.krx.dto.ApiResponse;
import com.flab.investing.stock.batch.infrastructure.krx.dto.Item;
import com.flab.investing.stock.batch.step.krx.StockInformationProcessor;
import com.flab.investing.stock.batch.step.krx.StockInformationReader;
import com.flab.investing.stock.batch.step.krx.StockInformationWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StockInformationStep {

    private final PlatformTransactionManager transactionManager;
    private final StockInformationReader stockInformationReader;
    private final StockInformationProcessor stockInformationProcessor;
    private final StockInformationWriter stockInformationWriter;
    private final JobRepository jobRepository;

    @Bean
    public Step stcokInformationStep() {
        return new StepBuilder("stcokInformationStep", jobRepository)
                .<ApiResponse, List<Item>>chunk(10_000)
                .reader(stockInformationReader)
                .processor(stockInformationProcessor)
                .writer(stockInformationWriter)
                .transactionManager(transactionManager)
                .build();
    }

}
