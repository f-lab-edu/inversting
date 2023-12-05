package com.flab.investing.stock.dao;

import com.flab.investing.stock.application.dto.TradeRequest;
import com.flab.investing.stock.infrastructure.AwsSqsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TradeDao {

    private final AwsSqsClient awsSqsClient;

    public void orderSend(TradeRequest tradeRequest) {
        log.info("====== 주식 주문 요청 ====> {}", tradeRequest);
        awsSqsClient.orderSend(tradeRequest);
    }

}
