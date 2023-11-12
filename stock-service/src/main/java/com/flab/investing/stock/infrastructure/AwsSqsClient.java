package com.flab.investing.stock.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.investing.global.error.exception.SerializerException;
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

    public SendResult<String> orderSend(final TradeRequest request) {
        log.info("message sent: {}", request);
        return template.send(to -> to
                .queue(queueName)
                .messageGroupId(request.id())
                .messageDeduplicationId(request.id())
                .payload(serializer(request)));
    }

    private String serializer(final TradeRequest request) {
        try {
            return new ObjectMapper().writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new SerializerException();
        }
    }

}
