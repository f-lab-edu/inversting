package com.flab.investing.stock.repository;

import com.flab.investing.stock.domain.entity.StockIntraday;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StockIntradayRepository extends MongoRepository<StockIntraday, String> {

    Optional<StockIntraday> findTopByStockIdOrderByCreatedAtDesc(Long stockId);
}
