package com.flab.investing.stock.infrastructure;

import com.flab.investing.global.error.exception.RabbitMqRetryException;
import com.flab.investing.stock.application.dto.TradeRequest;
import io.github.resilience4j.retry.annotation.Retry;
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

    @Retry(name = "rabbitmqRetry", fallbackMethod = "rabbitmqFallback")
    public void oderSend(TradeRequest tradeRequest) {
        log.info("message sent: {}", tradeRequest);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, tradeRequest);
    }

    private void rabbitmqFallback(TradeRequest tradeRequest, Exception exception) {
        log.error("주식 주문 요청에 실패하였습니다. : {}" , tradeRequest);
        throw new RabbitMqRetryException(exception.getMessage());
    }

}
