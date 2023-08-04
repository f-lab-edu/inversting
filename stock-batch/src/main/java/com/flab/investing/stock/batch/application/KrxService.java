package com.flab.investing.stock.batch.application;

import com.flab.investing.stock.batch.infrastructure.krx.KrxInfoClient;
import com.flab.investing.stock.batch.infrastructure.krx.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KrxService {

    private static final int searchCount = 10_000;

    private final KrxInfoClient krxInfoClient;

    @Value("${krx.key}")
    private String key;

    public ApiResponse send() {
        log.info("요청");
        return krxInfoClient.getItemInfo(key, searchCount, 1);
    }

}
