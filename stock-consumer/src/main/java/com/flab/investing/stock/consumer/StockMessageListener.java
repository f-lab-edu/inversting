package com.flab.investing.stock.consumer;

import com.flab.investing.stock.application.PurchaseService;
import com.flab.investing.stock.application.SalesService;
import com.flab.investing.stock.common.StockStatus;
import com.flab.investing.stock.common.TradeCode;
import com.flab.investing.stock.consumer.dto.TradeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockMessageListener {

    private final PurchaseService purchaseService;
    private final SalesService salesService;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(TradeResponse tradeResponse) {
        log.info("rabbitmq 받은값 ====> {}", tradeResponse);

        if(TradeCode.BUY.equals(tradeResponse.tradeCode())) {
            purchaseService.purchase(tradeResponse);
            return;
        }

        salesService.sales(tradeResponse);
    }

}
