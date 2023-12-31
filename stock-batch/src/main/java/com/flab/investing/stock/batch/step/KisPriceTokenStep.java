package com.flab.investing.stock.batch.step;

import com.flab.investing.stock.batch.application.KisService;
import com.flab.investing.stock.batch.application.StockIntradayService;
import com.flab.investing.stock.batch.application.StockService;
import com.flab.investing.stock.batch.domain.Stock;
import com.flab.investing.stock.batch.domain.StockPrice;
import com.flab.investing.stock.batch.domain.StockPriceMapper;
import com.flab.investing.stock.batch.domain.Token;
import com.flab.investing.stock.batch.domain.entity.StockIntraday;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class KisPriceTokenStep {

    private final SqlSessionFactory sqlSessionFactory;
    private final StockService stockService;
    private final KisService kisService;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final StockIntradayService stockIntradayService;

    private Token token;

    @Bean
    public Step kisTokenSearchStep() {
        return new StepBuilder("kisTokenSearchStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    this.token = kisService.getToken();
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step getKisPriceStep() {
        return new StepBuilder("getKisPriceStep", jobRepository)
                .<Stock, StockPrice>chunk(100, transactionManager)
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
                .pageSize(200)
                .build();
    }

    @Bean
    public ItemProcessor<Stock, StockPrice> stockStockPriceItemProcessor() {
        return stock -> stockService.searchPrice(stock, this.token);
    }

    @Bean
    public ItemWriter<StockPrice> stockPriceWriter() {
        return stockPrices -> {
            List<StockIntraday> stockIntradaies = stockPrices.getItems()
                    .stream()
                    .map(StockPriceMapper::mapper)
                    .collect(Collectors.toList());

            stockIntradayService.saveAll(stockIntradaies);
        };
    }

}
