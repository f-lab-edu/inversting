package com.flab.investing.stock.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.investing.global.common.ObjectMapperSerializer;
import com.flab.investing.global.error.exception.SerializerException;
import com.flab.investing.stock.application.TradeService;
import com.flab.investing.stock.consumer.dto.TradeException;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RollbackConsumer {

    private final TradeService tradeService;
    private final ObjectMapperSerializer objectMapperSerializer;

    @SqsListener("${cloud.sqs.rollback.queue.name}")
    public void tradeRollback(String message) {
        log.info("message : {}", message);
        TradeException tradeException = objectMapperSerializer.readValue(message, TradeException.class);
        tradeService.rollbackTrade(tradeException.tradeId());
    }

}
