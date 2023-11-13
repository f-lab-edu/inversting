package com.flab.investing.stock.infrastructure;

import com.flab.investing.global.common.ObjectMapperSerializer;
import com.flab.investing.stock.application.dto.TradeRequest;
import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsSqsClient {

    @Value("${cloud.sqs.queue.name}")
    private String queueName;

    private final SqsTemplate template;
    private final ObjectMapperSerializer objectMapperSerializer;

    public SendResult<String> orderSend(final TradeRequest request) {
        log.info("message sent: {}", request);
        return template.send(to -> to
                .queue(queueName)
                .messageGroupId(request.id())
                .messageDeduplicationId(request.id())
                .payload(objectMapperSerializer.serializer(request)));
    }

}
