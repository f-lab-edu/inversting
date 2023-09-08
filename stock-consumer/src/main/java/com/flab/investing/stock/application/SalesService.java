package com.flab.investing.stock.application;

import com.flab.investing.stock.common.StockStatus;
import com.flab.investing.stock.consumer.dto.TradeResponse;
import com.flab.investing.stock.domain.StockPurchase;
import com.flab.investing.stock.domain.StockSale;
import com.flab.investing.stock.domain.Trade;
import com.flab.investing.stock.repository.StockPurchaseRepository;
import com.flab.investing.stock.repository.StockSaleRepository;
import com.flab.investing.stock.repository.TradeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final StockPurchaseRepository stockPurchaseRepository;
    private final StockSaleRepository stockSaleRepository;
    private final TradeRepository tradeRepository;

    @Transactional
    public void sales(TradeResponse tradeResponse) {

        // 구매 가능한 것이 있는지 확인
        List<StockPurchase> availStocks = stockPurchaseRepository.findByStockIdAndStatus(tradeResponse.stockId(), StockStatus.ON_HOLD);

        int availSalesStocksCount = availStocks.size() - tradeResponse.stockCount();
        Trade trade = tradeRepository.findById(tradeResponse.tradeId())
                .orElseThrow(() -> new IllegalArgumentException("주식 정보가 없습니다."));

        // 구매가능한곳에서 모두 판매가 가능하다면, trade 상태를 변경시킨다.
        if(availSalesStocksCount >= 0) {
            IntStream.range(0, tradeResponse.stockCount())
                            .forEach(i -> availStocks.get(i).sales(tradeResponse.userId()));
            trade.execution();
            return;
        }

        IntStream.range(0, availStocks.size())
                .forEach(i -> availStocks.get(i).sales(tradeResponse.userId()));
        trade.execution(availSalesStocksCount);

        List<StockSale> stockSales = IntStream.range(0, Math.abs(availSalesStocksCount))
                .mapToObj(i -> new StockSale(
                        tradeResponse.userId(),
                        tradeResponse.stockId(),
                        tradeResponse.tradeId(),
                        tradeResponse.stockOfNumber()
                ))
                .collect(Collectors.toList());

        stockSaleRepository.saveAll(stockSales);
    }


}
