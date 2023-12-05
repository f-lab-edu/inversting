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
public class PurchaseService {

    private final StockPurchaseRepository stockPurchaseRepository;
    private final StockSaleRepository stockSaleRepository;
    private final TradeRepository tradeRepository;

    @Transactional
    public void purchase(TradeResponse tradeResponse) {
        List<StockSale> availableStocks = stockSaleRepository.findByStockIdAndStatus(tradeResponse.stockId(), StockStatus.ON_HOLD);

        int purchaseAvailableCount = availableStocks.size() - tradeResponse.stockCount();
        Trade trade = tradeRepository.findById(tradeResponse.tradeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주식입니다."));

        // 만약에 주문가능한게 더 많은 경우 모두 구매
        if (purchaseAvailableCount >= 0) {
            IntStream.range(0, tradeResponse.stockCount())
                    .forEach(i -> availableStocks.get(i).purchase(tradeResponse.userId()));
            trade.execution();

            return;
        }

        IntStream.range(0, availableStocks.size())
                .forEach(i -> availableStocks.get(i).purchase(tradeResponse.userId()));
        trade.execution(Math.abs(purchaseAvailableCount));

        List<StockPurchase> purchases = IntStream.range(0, Math.abs(purchaseAvailableCount))
                .mapToObj(i -> new StockPurchase(
                        tradeResponse.userId(),
                        tradeResponse.stockId(),
                        tradeResponse.tradeId(),
                        tradeResponse.stockOfNumber()
                ))
                .collect(Collectors.toList());

        stockPurchaseRepository.saveAll(purchases);
    }


}
