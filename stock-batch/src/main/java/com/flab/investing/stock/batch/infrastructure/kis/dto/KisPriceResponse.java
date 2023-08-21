package com.flab.investing.stock.batch.infrastructure.kis.dto;

public record KisPriceResponse(
        String rt_cd,
        String msg_cd,
        String msg1,
        KisPriceDetail output
) { }
