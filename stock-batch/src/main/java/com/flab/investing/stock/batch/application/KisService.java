package com.flab.investing.stock.batch.application;

import com.flab.investing.stock.batch.domain.Stock;
import com.flab.investing.stock.batch.domain.StockPrice;
import com.flab.investing.stock.batch.domain.Token;
import com.flab.investing.stock.batch.infrastructure.kis.KisTokenRepository;
import com.flab.investing.stock.batch.infrastructure.kis.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KisService {
    private final KisTokenRepository kisTokenRepository;

    public Token getToken() {
        return kisTokenRepository.findToken();
    }

    public StockPrice getPrice(final Token token, final Stock stock) {
        KisPriceDetail detail = kisTokenRepository.findPrice(token.accessToken(),
                stock.shortCode().substring(1));

        return new StockPrice(stock.id(),  stock.shortCode(), detail.stck_prpr(), detail.iscd_stat_cls_code(),
                detail.prdy_vrss_sign(), detail.acml_tr_pbmn(),
                detail.acml_vol(), detail.stck_hgpr(), detail.stck_lwpr(),
                detail.stck_mxpr(), detail.stck_llam());
    }

}
