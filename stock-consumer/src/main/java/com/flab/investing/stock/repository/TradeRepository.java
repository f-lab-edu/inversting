package com.flab.investing.stock.repository;

import com.flab.investing.stock.common.StockStatus;
import com.flab.investing.stock.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    boolean existsByIdAndStatus(Long id, StockStatus status);

}
