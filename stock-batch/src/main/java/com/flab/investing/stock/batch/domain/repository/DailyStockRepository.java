package com.flab.investing.stock.batch.domain.repository;

import com.flab.investing.stock.batch.domain.entity.DailyStock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface DailyStockRepository extends MongoRepository<DailyStock, String> {
}
