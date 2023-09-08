package com.flab.investing.stock.domain;

import com.flab.investing.global.common.BaseTimeEntity;
import com.flab.investing.stock.common.StockStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockSale extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purchase_user_id")
    private Long purchaseUserId;

    @Column(name = "sale_user_id", nullable = false)
    private Long saleUserId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StockStatus status;

    @Column(name = "stock_id", nullable = false)
    private Long stockId;

    @Column(name = "trade_id", nullable = false)
    private Long tradeId;

    @Column(name = "price", nullable = false)
    private Integer price;

    public StockSale(Long saleUserId, Long stockId, Long tradeId, Integer price) {
        this.saleUserId = saleUserId;
        this.status = StockStatus.ON_HOLD;
        this.stockId = stockId;
        this.tradeId = tradeId;
        this.price = price;
    }

    public void purchase(Long userId) {
        this.purchaseUserId = userId;
        this.status = StockStatus.EXECUTION;
    }
}
