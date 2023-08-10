package com.flab.investing.stock.batch.domain;

import lombok.Getter;

@Getter
public class StockPrice {

    private String code;
    private String amount;
    private String status;  // 종목 상태 구분 코드
    private String sign;    // 전일 대비 부호
    private String totalAmount; // 누적 거래 대금
    private String totalCount;  // 누적거래량
    private String stockHigh;   // 최고가
    private String stockLower;  // 최저가
    private String highLimit;  // 상한가
    private String lowerLimit; // 하한가

    public StockPrice(String code, String amount, String status,
                      String sign, String totalAmount, String totalCount,
                      String stockHigh, String stockLower, String highLimit,
                      String lowerLimit) {
        this.code = code;
        this.amount = amount;
        this.status = status;
        this.sign = sign;
        this.totalAmount = totalAmount;
        this.totalCount = totalCount;
        this.stockHigh = stockHigh;
        this.stockLower = stockLower;
        this.highLimit = highLimit;
        this.lowerLimit = lowerLimit;
    }
}
