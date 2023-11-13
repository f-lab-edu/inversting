package com.flab.investing.stock.repository;

import com.flab.investing.stock.domain.entity.DailyStock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyStockRepository extends MongoRepository<DailyStock, String> {

    List<DailyStock> findByStockIdAndCreatedAtBetween(Long stockId, LocalDate startDate, LocalDate endDate);

}
