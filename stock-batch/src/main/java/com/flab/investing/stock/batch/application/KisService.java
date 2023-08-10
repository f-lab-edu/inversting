package com.flab.investing.stock.batch.application;

import com.flab.investing.stock.batch.common.KisAccessProperties;
import com.flab.investing.stock.batch.domain.StockPrice;
import com.flab.investing.stock.batch.domain.Token;
import com.flab.investing.stock.batch.infrastructure.kis.KisTokenClient;
import com.flab.investing.stock.batch.infrastructure.kis.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KisService {

    private final static String GRANT_TYPE = "client_credentials";
    private final static String MARKET_CODE = "주식";

    private final KisTokenClient kisTokenClient;

    private final KisAccessProperties kisAccessProperties;

    public Token getToken() {
        KisTokenResponse token = kisTokenClient.getToken(new KisTokenRequest(
                GRANT_TYPE,
                kisAccessProperties.getAppKey(),
                kisAccessProperties.getSecretKey()));

        return new Token(token.access_token());
    }

    public StockPrice getPrice(Token token, String code) {
        KisPriceHeaderRequest header = new KisPriceHeaderRequest(token.getAccessToken(),
                kisAccessProperties.getAppKey(),
                kisAccessProperties.getSecretKey(),
                code);

        KisPriceResponse response = kisTokenClient.getPrice(header, MARKET_CODE, code);
        KisPriceDetail detail = response.output();

        return new StockPrice(code, detail.stck_prpr(), detail.iscd_stat_cls_code(),
                detail.prdy_vrss_sign(), detail.acml_tr_pbmn(),
                detail.acml_vol(), detail.stck_hgpr(), detail.stck_lwpr(),
                detail.stck_mxpr(), detail.stck_llam());
    }

}
