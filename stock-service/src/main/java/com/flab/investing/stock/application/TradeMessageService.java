package com.flab.investing.stock.application;

import com.flab.investing.stock.common.TradeCode;
import com.flab.investing.stock.application.dto.TradeRequest;
import com.flab.investing.stock.controller.request.StockPurchaseRequest;
import com.flab.investing.stock.dao.TradeDao;
import com.flab.investing.stock.infrastructure.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeMessageService {

    private final TradeDao tradeDao;

    public void purchaseSend(UserResponse userResponse, StockPurchaseRequest request, Long tradeId) {
        TradeRequest tradeRequest = new TradeRequest(
                tradeId,
                request.stockId(),
                userResponse.userId(),
                request.stockOfAmount(),
                request.stockCount(),
                TradeCode.BUY
        );

        tradeDao.purchaseSend(tradeRequest);
    }
}
