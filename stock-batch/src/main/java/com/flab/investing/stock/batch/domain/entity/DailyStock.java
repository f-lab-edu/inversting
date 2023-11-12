package com.flab.investing.stock.batch.domain.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "dailyStock")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyStock extends MongoBaseEntity{

    @Id
    private String id;

    private Long stockId;
    private String code;
    private Integer startAmount;
    private Integer lastAmount;
    private String sign;
    private Integer stockHigh;
    private Integer stockLower;
    private Integer highLimit;
    private Integer lowerLimit;
    private Long totalTrading;
    private BigDecimal totalAmount;

    public DailyStock(Long stockId,
                      String code,
                      Integer startAmount,
                      Integer lastAmount,
                      String sign,
                      Integer stockHigh,
                      Integer stockLower,
                      Integer highLimit,
                      Integer lowerLimit,
                      Long totalTrading,
                      BigDecimal totalAmount) {
        this.stockId = stockId;
        this.code = code;
        this.startAmount = startAmount;
        this.lastAmount = lastAmount;
        this.sign = sign;
        this.stockHigh = stockHigh;
        this.stockLower = stockLower;
        this.highLimit = highLimit;
        this.lowerLimit = lowerLimit;
        this.totalTrading = totalTrading;
        this.totalAmount = totalAmount;
    }
}
