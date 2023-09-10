package com.flab.investing.stock.infrastructure;

import com.flab.investing.stock.application.dto.TradeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqRepository {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void purchaseSend(TradeRequest tradeRequest) {
        log.info("message sent: {}", tradeRequest);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, tradeRequest);
    }

}
