package com.flab.investing.stock.domain.entity;

import com.flab.investing.global.jpa.common.BaseTimeEntity;
import com.flab.investing.stock.common.StockStatus;
import com.flab.investing.stock.common.DivisionStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Trade extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock_id", nullable = false)
    private Long stockId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "progress_count", nullable = false)
    private int progressCount;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Column(name = "trade_time", nullable = false)
    private LocalDateTime tradeTime;

    @Column(name = "division", nullable = false)
    @Enumerated(EnumType.STRING)
    private DivisionStatus division;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StockStatus status;

    public Trade(Long stockId, Long userId, int count,
                 int price, int totalPrice, DivisionStatus division) {
        this.stockId = stockId;
        this.userId = userId;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.division = division;

        this.progressCount = 0;
        this.status = StockStatus.ON_HOLD;
        this.tradeTime = LocalDateTime.now();
    }

    public void rollback() {
        this.status = StockStatus.ROOLBACK;
    }
}
