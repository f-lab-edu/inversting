package com.flab.investing.stock.batch.step;

import com.flab.investing.stock.batch.application.KisService;
import com.flab.investing.stock.batch.application.StockService;
import com.flab.investing.stock.batch.domain.Stock;
import com.flab.investing.stock.batch.domain.StockPrice;
import com.flab.investing.stock.batch.domain.Token;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KisPriceTokenStep {

    private final SqlSessionFactory sqlSessionFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final StockService stockService;
    private final KisService kisService;

    private Token token;

    @Bean
    public Step kisTokenSearchStep() {
        return stepBuilderFactory.get("kisTokenSearchStep")
                .tasklet((contribution, chunkContext) -> {
                    this.token = kisService.getToken();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step getKisPriceStep() {
        return stepBuilderFactory.get("getKisPriceStep")
                .<Stock, StockPrice>chunk(1)
                .reader(stockPriceReader())
                .processor(stockStockPriceItemProcessor())
                .writer(stockPriceWriter())
                .build();
    }

    @Bean
    public MyBatisPagingItemReader<Stock> stockPriceReader() {
        return new MyBatisPagingItemReaderBuilder<Stock>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.flab.investing.stock.batch.infrastructure.stock.StockMapper.findAll")
                .build();
    }

    @Bean
    public ItemProcessor<Stock, StockPrice> stockStockPriceItemProcessor() {
        return stock -> stockService.searchPrice(stock, this.token);
    }

    @Bean
    public ItemWriter<StockPrice> stockPriceWriter() {
        return stockPrices -> stockPrices.forEach(stockService::save);
    }

}
