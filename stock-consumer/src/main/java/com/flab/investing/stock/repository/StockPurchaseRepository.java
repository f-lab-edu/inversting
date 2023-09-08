package com.flab.investing.stock.repository;

import com.flab.investing.stock.common.StockStatus;
import com.flab.investing.stock.domain.StockPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockPurchaseRepository extends JpaRepository<StockPurchase, Long> {

    List<StockPurchase> findByStockIdAndStatus(Long stockId, StockStatus status);

}
