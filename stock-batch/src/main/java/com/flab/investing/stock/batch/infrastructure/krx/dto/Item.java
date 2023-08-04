package com.flab.investing.stock.batch.infrastructure.krx.dto;

public record Item(
        String basDt,
        String srtnCd,
        String isinCd,
        String mrktCtg,
        String itmsNm,
        String crno,
        String corpNm) {}
