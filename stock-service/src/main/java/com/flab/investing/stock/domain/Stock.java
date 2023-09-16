package com.flab.investing.stock.domain;

import com.flab.investing.global.jpa.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "short_code")
    private String shortCode;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "corporation_code")
    private String corporationCode;

    @Column(name = "stock_high")
    private Integer stockHigh;

    @Column(name = "stock_lower")
    private Integer stockLower;

    @Column(name = "high_limit")
    private Integer highLimit;

    @Column(name = "lower_limit")
    private Integer lowerLimit;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status;

    public Stock(String code, String shortCode, String name, String shortName, String corporationCode, Integer stockHigh, Integer stockLower, Integer highLimit, Integer lowerLimit, Integer price) {
        this.code = code;
        this.shortCode = shortCode;
        this.name = name;
        this.shortName = shortName;
        this.corporationCode = corporationCode;
        this.stockHigh = stockHigh;
        this.stockLower = stockLower;
        this.highLimit = highLimit;
        this.lowerLimit = lowerLimit;
        this.price = price;
        this.status = "RUNNING";
    }
}
