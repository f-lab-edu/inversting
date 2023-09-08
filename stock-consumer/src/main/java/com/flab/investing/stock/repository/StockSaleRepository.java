package com.flab.investing.stock.repository;

import com.flab.investing.stock.common.StockStatus;
import com.flab.investing.stock.domain.StockSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockSaleRepository extends JpaRepository<StockSale, Long> {

    List<StockSale> findByStockIdAndStatus(Long stockId, StockStatus status);

}
