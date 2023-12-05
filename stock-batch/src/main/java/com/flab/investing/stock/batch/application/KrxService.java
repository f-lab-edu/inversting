package com.flab.investing.stock.batch.application;

import com.flab.investing.stock.batch.infrastructure.krx.KrxInfoRepository;
import com.flab.investing.stock.batch.infrastructure.krx.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KrxService {

    private final KrxInfoRepository krxInfoRepository;

    @Value("${krx.key}")
    private String key;

    public ApiResponse send() {
        return krxInfoRepository.findStocks(key);
    }

}
