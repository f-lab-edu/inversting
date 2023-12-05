package com.flab.investing.stock.batch.infrastructure.kis.dto;

public record KisPriceHeaderRequest(
        String authorization,
        String appkey,
        String appsecret,
        String tr_id
) {}
