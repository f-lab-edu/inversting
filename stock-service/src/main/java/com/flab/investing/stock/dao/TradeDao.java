package com.flab.investing.stock.dao;

import com.flab.investing.stock.application.dto.TradeRequest;
import com.flab.investing.stock.infrastructure.RabbitMqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TradeDao {

    private final RabbitMqRepository rabbitMqRepository;

    public void purchaseSend(TradeRequest tradeRequest) {
        log.info("====== 구매 요청 ====> {}", tradeRequest);
        rabbitMqRepository.purchaseSend(tradeRequest);
        log.info("====== 구매 요청 성공");
    }

}
