package com.flab.investing.stock.batch.application;

import com.flab.investing.stock.batch.infrastructure.krx.KrxInfoClient;
import com.flab.investing.stock.batch.infrastructure.krx.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KrxService {

    private static final int SEARCH_COUNT = 10_000;
    private static final int DEFAULT_PAGE_NO = 1;
    private static final String JSON = "json";

    private final KrxInfoClient krxInfoClient;

    @Value("${krx.key}")
    private String key;

    public ApiResponse send() {
        log.info("한국 거래소 API 요청");
        return krxInfoClient.getItemInfo(key, SEARCH_COUNT, DEFAULT_PAGE_NO, JSON);
    }

}
