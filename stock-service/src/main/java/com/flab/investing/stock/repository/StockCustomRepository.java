package com.flab.investing.stock.repository;

import com.flab.investing.stock.controller.response.StockInfoResponse;
import com.flab.investing.stock.controller.response.StocksResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.flab.investing.stock.domain.QStock.stock;

@Repository
@RequiredArgsConstructor
public class StockCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<StocksResponse> findAllByPageable(Pageable pageable) {
        return jpaQueryFactory.select(Projections.constructor(StocksResponse.class,
                        stock.id,
                        stock.code,
                        stock.name,
                        stock.price
                ))
                .from(stock)
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public StockInfoResponse findByStockId(Long stockId) {
        return jpaQueryFactory.select(Projections.constructor(StockInfoResponse.class,
                        stock.id,
                        stock.name,
                        stock.corporationCode,
                        stock.status,
                        stock.price,
                        stock.stockHigh,
                        stock.stockLower,
                        stock.highLimit,
                        stock.lowerLimit
                ))
                .from(stock)
                .where(stock.id.eq(stockId))
                .fetchOne();
    }

}
