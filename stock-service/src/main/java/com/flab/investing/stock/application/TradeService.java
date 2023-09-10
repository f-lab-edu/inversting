package com.flab.investing.stock.application;

import com.flab.investing.stock.application.dto.TradeData;
import com.flab.investing.stock.common.DivisionStatus;
import com.flab.investing.stock.domain.Trade;
import com.flab.investing.stock.repository.TradeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TradeService {

    private final TradeRepository tradeRepository;

    @Transactional
    public Long saveAndGetId(TradeData tradeData) {
        Trade trade = tradeRepository.save(new Trade(
                tradeData.stockId(),
                tradeData.userId(),
                tradeData.count(),
                tradeData.price(),
                tradeData.price() * tradeData.count(),
                DivisionStatus.BUY
        ));

        return trade.getId();
    }

}
