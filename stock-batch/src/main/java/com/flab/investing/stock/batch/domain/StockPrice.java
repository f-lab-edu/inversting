package com.flab.investing.stock.batch.domain;

public record StockPrice(
        Long stockId,
        String code,
        String amount,
        String status,  // 종목 상태 구분 코드
        String sign,    // 전일 대비 부호
        String totalAmount, // 누적 거래 대금
        String totalCount,  // 누적거래량
        String stockHigh,   // 최고가
        String stockLower,  // 최저가
        String highLimit,  // 상한가
        String lowerLimit // 하한가
) {
}
