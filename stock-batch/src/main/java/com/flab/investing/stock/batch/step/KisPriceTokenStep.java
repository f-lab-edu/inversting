package com.flab.investing.stock.batch.step;

import com.flab.investing.stock.batch.application.StockService;
import com.flab.investing.stock.batch.domain.Stock;
import com.flab.investing.stock.batch.domain.Token;
import com.flab.investing.stock.batch.step.tasklet.KisTokenTasklet;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@JobScope
public class KisPriceTokenStep {

    private final SqlSessionFactory sqlSessionFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final KisTokenTasklet kisTokenTasklet;
    private final StockService stockService;
    private final StepExecution stepExecution;

    @Bean
    public Step kisPriceTokenStep() {
        return stepBuilderFactory.get("kisPriceTokenStep")
                .tasklet(kisTokenTasklet)
                .build();
    }

    @Bean
    public Step getKisPriceStep() {
        return stepBuilderFactory.get("getKisPriceStep")
                .chunk(1000)
                .reader(stockPriceReader())
                .writer()
    }

    @Bean
    @StepScope
    public MyBatisPagingItemReader<Stock> stockPriceReader() {
        return new MyBatisPagingItemReaderBuilder<Stock>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.flab.investing.stock.batch.infrastructure.stock.StockMapper.findAll")
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<Stock> stockPriceWriter() {
        Token token = (Token) stepExecution.getJobExecution().getExecutionContext().get("token");
        return stocks -> stocks.forEach(stock -> stockService.searchPrice(stock, token));
    }

}
