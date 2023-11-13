package com.flab.investing.global.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.investing.global.error.exception.SerializerException;
import com.flab.investing.stock.application.dto.TradeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectMapperSerializer {

    private final ObjectMapper objectMapper;

    public <T> String serializer(final T request) {
        try {
            return new ObjectMapper().writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new SerializerException();
        }
    }

}
