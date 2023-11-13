package com.flab.investing.stock.repository;

import com.flab.investing.stock.domain.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockRepository extends JpaRepository<Stock, Long> {
}
