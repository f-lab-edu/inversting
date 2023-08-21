package com.flab.investing.stock.batch.infrastructure.kis.dto;

public record KisTokenResponse(
        String access_token,
        String access_token_token_expired,
        String token_type,
        String expires_in
) { }