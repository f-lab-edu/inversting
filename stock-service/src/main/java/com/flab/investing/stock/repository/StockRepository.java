package com.flab.investing.stock.repository;

import com.flab.investing.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockRepository extends JpaRepository<Stock, Long> {
}
