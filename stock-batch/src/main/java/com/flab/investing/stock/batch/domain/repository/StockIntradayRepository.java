package com.flab.investing.stock.batch.domain.repository;

import com.flab.investing.stock.batch.domain.entity.StockIntraday;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockIntradayRepository extends MongoRepository<StockIntraday, String> {

    List<StockIntraday> findByStockIdAndDate(Long stockId, String date);
}
