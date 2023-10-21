package com.flab.investing.stock.consumer;

import com.flab.investing.stock.application.PurchaseService;
import com.flab.investing.stock.application.SalesService;
import com.flab.investing.stock.common.StockStatus;
import com.flab.investing.stock.common.TradeCode;
import com.flab.investing.stock.consumer.dto.TradeResponse;
import com.flab.investing.stock.evnet.dto.TradeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockMessageListener {

    private final PurchaseService purchaseService;
    private final SalesService salesService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(final TradeResponse tradeResponse) {
        log.info("rabbitmq 받은값 ====> {}", tradeResponse);

        try {
            if (TradeCode.BUY.equals(tradeResponse.tradeCode())) {
                purchaseService.purchase(tradeResponse);
                return;
            }

            salesService.sales(tradeResponse);
        } catch (Exception e) {
            log.error("에러가 발생하였습니다. [{}]", e.getMessage());
            applicationEventPublisher.publishEvent(new TradeException(
                    tradeResponse.tradeId(),
                    tradeResponse.stockId(),
                    tradeResponse.userId(),
                    e.getMessage()
            ));
        }
    }

}
