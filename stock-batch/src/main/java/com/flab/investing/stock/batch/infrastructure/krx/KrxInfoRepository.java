package com.flab.investing.stock.batch.infrastructure.krx;

import com.flab.investing.stock.batch.infrastructure.krx.constant.KrxProperties;
import com.flab.investing.stock.batch.infrastructure.krx.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KrxInfoRepository {

    private static final String JSON = "json";

    private final KrxInfoClient krxInfoClient;

    public ApiResponse findStocks(final String key) {
        return krxInfoClient.getItemInfo(key,
                KrxProperties.SEARCH_COUNT.getValue(),
                KrxProperties.DEFAULT_PAGE_NO.getValue(),
                JSON);
    }

}
