package com.flab.investing.stock.domain;

import com.flab.investing.global.common.BaseTimeEntity;
import com.flab.investing.stock.common.DivisionStatus;
import com.flab.investing.stock.common.StockStatus;
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

    public void execution() {
        this.progressCount = count;
        this.status = StockStatus.EXECUTION;
    }

    public void execution(int count) {
        this.progressCount += count;
    }

}
