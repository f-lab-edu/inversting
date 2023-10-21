package com.flab.investing.stock.application;

import com.flab.investing.stock.common.StockStatus;
import com.flab.investing.stock.consumer.dto.TradeResponse;
import com.flab.investing.stock.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TradeService {

    private final TradeRepository tradeRepository;

    public boolean isExistTradeAndHoldStatus(Long tradeId) {
        return tradeRepository.existsByIdAndStatus(tradeId, StockStatus.ON_HOLD);
    }

}
