package com.flab.investing.stock.batch.infrastructure.kis.dto;

public record KisTokenRequest(
        String grant_type,
        String appkey,
        String appsecret ) {
}
