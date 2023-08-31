package com.flab.investing.stock.batch.infrastructure.kis;

import com.flab.investing.stock.global.config.KisAccessConfig;
import com.flab.investing.stock.batch.domain.Token;
import com.flab.investing.stock.batch.infrastructure.kis.constant.KisProperties;
import com.flab.investing.stock.batch.infrastructure.kis.dto.KisPriceDetail;
import com.flab.investing.stock.batch.infrastructure.kis.dto.KisPriceResponse;
import com.flab.investing.stock.batch.infrastructure.kis.dto.KisTokenRequest;
import com.flab.investing.stock.batch.infrastructure.kis.dto.KisTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KisTokenRepository {

    private final KisTokenClient kisTokenClient;
    private final KisAccessConfig kisAccessProperties;

    public Token findToken() {
        final KisTokenResponse token = kisTokenClient.getToken(new KisTokenRequest(
                KisProperties.GRANT_TYPE.getValue(),
                kisAccessProperties.getAppKey(),
                kisAccessProperties.getSecretKey()));

        return new Token(token.access_token());
    }

    public KisPriceDetail findPrice(final String accessToken, final String code) {
        final KisPriceResponse response = kisTokenClient.getPrice(
                kisAccessProperties.getSecretKey(),
                kisAccessProperties.getAppKey(),
                KisProperties.BEARER.getValue() + accessToken,
                KisProperties.TRACE_ID.getValue(),
                KisProperties.MARKET_CODE.getValue(),
                code);

        return response.output();
    }

}
