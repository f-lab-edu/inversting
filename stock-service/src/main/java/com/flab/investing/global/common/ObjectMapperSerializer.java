package com.flab.investing.global.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.investing.global.error.exception.SerializerException;
import com.flab.investing.stock.application.dto.TradeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ObjectMapperSerializer {

    private final ObjectMapper objectMapper;

    public <T> String serializer(final T request) {
        try {
            return objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException exception) {
            log.error("request: {}", request);
            throw new SerializerException(exception.getMessage());
        }
    }

    public <T> T readValue(String message, Class<T> clazz) {
        try {
            return objectMapper.readValue(message, clazz);
        } catch (JsonProcessingException exception) {
            log.error("에러난 메시지 : {}", message);
            throw new SerializerException(exception.getMessage());
        }
    }

}
