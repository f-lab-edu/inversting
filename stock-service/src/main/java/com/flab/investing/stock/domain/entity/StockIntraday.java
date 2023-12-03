package com.flab.investing.stock.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "stockIntraday")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockIntraday {

    @Id
    private String id;

    private Long stockId;
    private String date;
    private String code;
    private String amount;
    private String status;
    private String sign;
    private String highLimit;
    private String lowerLimit;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public StockIntraday(final String id,
                         final Long stockId,
                         final String date,
                         final String code,
                         final String amount,
                         final String status,
                         final String sign,
                         final String highLimit,
                         final String lowerLimit,
                         final LocalDateTime createdAt,
                         final LocalDateTime updatedAt) {
        this.id = id;
        this.stockId = stockId;
        this.date = date;
        this.code = code;
        this.amount = amount;
        this.status = status;
        this.sign = sign;
        this.highLimit = highLimit;
        this.lowerLimit = lowerLimit;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
