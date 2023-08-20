package com.flab.investing.stock.application;

import com.flab.investing.stock.application.dto.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeMessageService {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void purchaseSend(PurchaseRequest purchaseRequest) {
        log.info("message sent: {}", purchaseRequest);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, purchaseRequest);
    }
}
