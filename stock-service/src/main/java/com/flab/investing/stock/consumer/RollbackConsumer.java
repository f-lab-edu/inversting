package com.flab.investing.stock.consumer;

import com.flab.investing.stock.application.TradeService;
import com.flab.investing.stock.consumer.dto.TradeException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RollbackConsumer {

    private final TradeService tradeService;

    @RabbitListener(queues = "${rabbitmq.queue.rollback.name}")
    public void tradeRollback(TradeException tradeException) {
        tradeService.rollbackTrade(tradeException.tradeId());
    }

}
