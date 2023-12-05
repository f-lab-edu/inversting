package com.flab.investing.stock.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.investing.global.common.ObjectMapperSerializer;
import com.flab.investing.global.error.exception.SerializerException;
import com.flab.investing.stock.application.PurchaseService;
import com.flab.investing.stock.application.SalesService;
import com.flab.investing.stock.application.TradeService;
import com.flab.investing.stock.common.TradeCode;
import com.flab.investing.stock.consumer.dto.TradeResponse;
import com.flab.investing.stock.evnet.dto.TradeException;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockMessageListener {

    private final PurchaseService purchaseService;
    private final SalesService salesService;
    private final TradeService tradeService;
    private final ObjectMapperSerializer objectMapperSerializer;
    private final ApplicationEventPublisher applicationEventPublisher;

    @SqsListener("${cloud.sqs.rollback.queue.name}")
    public void receiveMessage(final String message) {
        log.info("전달받은 데이터 : {}", message);
        final TradeResponse tradeResponse = objectMapperSerializer.readValue(message, TradeResponse.class);

        if (!tradeService.isExistTradeAndHoldStatus(tradeResponse.tradeId())) {
            return;
        }

        this.process(tradeResponse);
    }

    private void process(final TradeResponse tradeResponse) {
        try {
            if (TradeCode.BUY.equals(tradeResponse.tradeCode())) {
                purchaseService.purchase(tradeResponse);
                return;
            }

            salesService.sales(tradeResponse);

        } catch (Exception e) {
            log.error("에러가 발생하였습니다. [{}]", e.getMessage());
            applicationEventPublisher.publishEvent(new TradeException(
                    UUID.randomUUID().toString(),
                    tradeResponse.tradeId(),
                    tradeResponse.stockId(),
                    tradeResponse.userId(),
                    e.getMessage()
            ));
        }
    }

}
