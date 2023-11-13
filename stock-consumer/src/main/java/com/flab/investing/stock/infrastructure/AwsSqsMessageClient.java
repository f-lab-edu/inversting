package com.flab.investing.stock.infrastructure;

import com.flab.investing.global.common.ObjectMapperSerializer;
import com.flab.investing.global.error.exception.AwsSqsRetryException;
import com.flab.investing.stock.evnet.dto.TradeException;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsSqsMessageClient {

    @Value("${cloud.sqs.rollback.queue.name}")
    private String queueName;

    private final SqsTemplate template;
    private final ObjectMapperSerializer objectMapperSerializer;

    @Retry(name = "awsSqsRetry", fallbackMethod = "awsSqsRetryFallback")
    public void rollbackSend(TradeException tradeException) {
        log.info("message sent: {}", tradeException);
        template.send(to -> to
                .queue(queueName)
                .messageGroupId(tradeException.id())
                .messageDeduplicationId(tradeException.id())
                .payload(objectMapperSerializer.serializer(tradeException)));
    }

    private void awsSqsRetryFallback(TradeException tradeRequest, Exception exception) {
        log.error("주식 주문 요청에 실패하였습니다. : {}", tradeRequest);
        throw new AwsSqsRetryException();
    }


}
