package com.flab.investing.stock.batch.step;

import com.flab.investing.stock.batch.application.DailyStockService;
import com.flab.investing.stock.batch.application.StockIntradayService;
import com.flab.investing.stock.batch.domain.DailyTrade;
import com.flab.investing.stock.batch.domain.StockInradays;
import com.flab.investing.stock.batch.domain.entity.DailyStock;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class DailyStocksStatisticsStep {

    private final SqlSessionFactory sqlSessionFactory;
    private final JobRepository jobRepository;
    private final StockIntradayService stockIntradayService;
    private final DailyStockService dailyStockService;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step dailyStocksStatistics() {
        return new StepBuilder("dailyStocksStatistics", jobRepository)
                .<DailyTrade, DailyStock>chunk(1000, transactionManager)
                .reader(dailyStockReader())
                .processor(dailyStockItemProcessor())
                .writer(dailyStockItemWriter())
                .build();
    }

    @Bean
    public MyBatisPagingItemReader<DailyTrade> dailyStockReader() {
        String searchDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        return new MyBatisPagingItemReaderBuilder<DailyTrade>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.flab.investing.stock.batch.infrastructure.stock.StockMapper.findStatisticsByToday")
                .parameterValues(Map.of("searchDate", searchDate))
                .pageSize(1000)
                .build();
    }

    @Bean
    public ItemProcessor<DailyTrade, DailyStock> dailyStockItemProcessor() {
        return dailyTrade -> {
            String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            StockInradays stockInradays = stockIntradayService.findByStockId(dailyTrade, today);

            return new DailyStock(
                    dailyTrade.stockId(),
                    dailyTrade.code(),
                    stockInradays.getStartAmount(),
                    stockInradays.getLastAmount(),
                    stockInradays.getSign(),
                    stockInradays.getHighLimit(),
                    stockInradays.getLowerLimit(),
                    dailyTrade.stockHigh(),
                    dailyTrade.stockLower(),
                    dailyTrade.totalTrading(),
                    dailyTrade.totalAmount()
            );
        };
    }

    @Bean
    public ItemWriter<DailyStock> dailyStockItemWriter() {
        return dailyStokcs -> dailyStokcs.forEach(dailyStockService::save);
    }

}
