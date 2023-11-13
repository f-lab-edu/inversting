package com.flab.investing.stock.domain.mappper;

import com.flab.investing.stock.controller.response.DailyStatisticsResponse;
import com.flab.investing.stock.domain.DailyStockStatistic;
import com.flab.investing.stock.domain.entity.DailyStock;
import org.mapstruct.Mapper;

@Mapper
public interface DailyStockMapper {

    DailyStockStatistic toDailyStockStatistic(DailyStock dailyStock);

    DailyStatisticsResponse.DailyStatisticsData toDailyStatisticsResponse(DailyStockStatistic dailyStockStatistic);

}
