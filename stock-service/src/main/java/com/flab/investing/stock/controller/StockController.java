package com.flab.investing.stock.controller;

import com.flab.investing.global.error.exception.InvalidJwtException;
import com.flab.investing.stock.application.*;
import com.flab.investing.stock.application.dto.TradeData;
import com.flab.investing.stock.common.DivisionStatus;
import com.flab.investing.stock.controller.request.StockPurchaseRequest;
import com.flab.investing.stock.controller.request.StockSellRequest;
import com.flab.investing.stock.controller.response.StockInfoResponse;
import com.flab.investing.stock.controller.response.StockListInfo;
import com.flab.investing.stock.controller.response.StockPurchaseResponse;
import com.flab.investing.stock.controller.response.StocksResponse;
import com.flab.investing.stock.domain.entity.Stock;
import com.flab.investing.stock.domain.entity.StockIntraday;
import com.flab.investing.stock.infrastructure.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.flab.investing.stock.controller.response.ResponseCode.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;
    private final UserService userService;
    private final TradeService tradeService;
    private final StockIntraDayService stockIntraDayService;
    private final TradeMessageService tradeMessageService;

    @GetMapping
    public ResponseEntity<StockListInfo> stockList(@PageableDefault(size = 7) Pageable pageable) {
        List<StocksResponse> stocksResponses = stockService.findAllPageable(pageable).stream()
                .map(stock -> new StocksResponse(
                        stock.getId(),
                        stock.getCode(),
                        stock.getName(),
                        Integer.parseInt(stockIntraDayService.findByStockId(stock.getId()).getAmount())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new StockListInfo(
                SUCCESS.getCode(),
                SUCCESS.getMessage(),
                stocksResponses
        ));
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockInfoResponse> stockInfo(@PathVariable Long stockId) {
        Stock stock = stockService.findByStockId(stockId);
        StockIntraday stockIntraday = stockIntraDayService.findByStockId(stockId);

        return ResponseEntity.ok(new StockInfoResponse(
                SUCCESS.getCode(),
                SUCCESS.getMessage(),
                stock.getId(),
                stock.getName(),
                stock.getCorporationCode(),
                stock.getStatus(),
                Integer.parseInt(stockIntraday.getAmount()),
                Integer.parseInt(Objects.toString(stock.getStockHigh(), stockIntraday.getAmount())),
                Integer.parseInt(Objects.toString(stock.getStockLower(), stockIntraday.getAmount())),
                Integer.parseInt(stockIntraday.getHighLimit()),
                Integer.parseInt(stockIntraday.getLowerLimit())
        ));
    }

    @PostMapping("/purchases")
    public ResponseEntity<StockPurchaseResponse> purchase(@RequestHeader String accessToken,
                                                          @RequestBody StockPurchaseRequest request) {
        final UserResponse userResponse = userService.tokenSend(accessToken);
        if (!SUCCESS.getCode().equals(userResponse.code())) {
            throw new InvalidJwtException(userResponse.message());
        }

        Long tradeId = tradeService.saveAndGetId(new TradeData(
                request.stockId(),
                userResponse.id(),
                request.stockCount(),
                request.stockOfAmount(),
                DivisionStatus.BUY
        ));

        tradeMessageService.purchaseSend(userResponse, request, tradeId);

        return ResponseEntity.ok(new StockPurchaseResponse(
                SUCCESS.getCode(), SUCCESS.getMessage(), request.stockId(),
                request.stockCount() * request.stockOfAmount(), request.stockOfAmount(), request.stockCount()));
    }

    @PostMapping("/selles")
    public ResponseEntity<StockPurchaseResponse> selles(@RequestHeader String accessToken,
                                                        @RequestBody StockSellRequest request) {
        final UserResponse userResponse = userService.tokenSend(accessToken);
        if (!SUCCESS.getCode().equals(userResponse.code())) {
            throw new InvalidJwtException(userResponse.message());
        }

        Long tradeId = tradeService.saveAndGetId(new TradeData(
                request.stockId(),
                userResponse.id(),
                request.stockCount(),
                request.stockOfAmount(),
                DivisionStatus.SELL
        ));

        tradeMessageService.sellSend(userResponse, request, tradeId);

        return ResponseEntity.ok(new StockPurchaseResponse(
                SUCCESS.getCode(), SUCCESS.getMessage(), request.stockId(),
                request.stockCount() * request.stockOfAmount(), request.stockOfAmount(), request.stockCount()));
    }

}
