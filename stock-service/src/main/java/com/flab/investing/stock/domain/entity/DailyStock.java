package com.flab.investing.stock.domain.entity;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Document(collection = "dailyStock")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyStock {

    @Id
    private String id;

    private Long stockId;
    private String code;
    private String baseDate;
    private Integer startAmount;
    private Integer lastAmount;
    private String sign;
    private Integer stockHigh;
    private Integer stockLower;
    private Integer highLimit;
    private Integer lowerLimit;
    private Long totalTrading;
    private BigDecimal totalAmount;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public DailyStock(final Long stockId,
                      final String code,
                      final String baseDate,
                      final Integer startAmount,
                      final Integer lastAmount,
                      final String sign,
                      final Integer stockHigh,
                      final Integer stockLower,
                      final Integer highLimit,
                      final Integer lowerLimit,
                      final Long totalTrading,
                      final BigDecimal totalAmount) {
        this.stockId = stockId;
        this.code = code;
        this.baseDate = baseDate;
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
