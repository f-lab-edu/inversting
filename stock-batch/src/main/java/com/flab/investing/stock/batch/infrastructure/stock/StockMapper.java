package com.flab.investing.stock.batch.infrastructure.stock;

import com.flab.investing.stock.batch.domain.DailyTrade;
import com.flab.investing.stock.batch.domain.Stock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper {

    void insert(final List<Stock> stocks);

    List<Stock> findAll();

    List<DailyTrade> findStatisticsByToday();
}
