package com.flab.investing.stock.controller;

import com.flab.investing.global.error.exception.InvalidJwtException;
import com.flab.investing.stock.application.StockService;
import com.flab.investing.stock.application.TradeMessageService;
import com.flab.investing.stock.application.UserService;
import com.flab.investing.stock.application.dto.PurchaseRequest;
import com.flab.investing.stock.controller.request.StockPurchaseRequest;
import com.flab.investing.stock.controller.response.ResponseCode;
import com.flab.investing.stock.controller.response.StockInfoResponse;
import com.flab.investing.stock.controller.response.StockPurchaseResponse;
import com.flab.investing.stock.controller.response.StocksResponse;
import com.flab.investing.stock.domain.Stock;
import com.flab.investing.stock.infrastructure.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.flab.investing.stock.controller.response.ResponseCode.SUCCESS;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final UserService userService;
    private final TradeMessageService tradeMessageService;

    @GetMapping("/list")
    public ResponseEntity<List<StocksResponse>> stockList(@PageableDefault(size = 7) Pageable pageable) {
        return ResponseEntity.ok(stockService.findAllPageable(pageable).stream()
                .map(stock -> new StocksResponse(stock.getId(), stock.getCode(), stock.getName(), stock.getPrice()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockInfoResponse> stockInfo(@PathVariable Long stockId) {
        Stock stock = stockService.findByStockId(stockId);

        return ResponseEntity.ok(new StockInfoResponse(
                stock.getId(),
                stock.getName(),
                stock.getCorporationCode(),
                stock.getStatus(),
                stock.getPrice(),
                stock.getStockHigh(),
                stock.getStockLower(),
                stock.getHighLimit(),
                stock.getLowerLimit()
        ));
    }

    @PostMapping("/purchase")
    public ResponseEntity<StockPurchaseResponse> purchase(@RequestHeader String accessToken,
                                                          @RequestBody StockPurchaseRequest request) {
        final UserResponse userResponse = userService.tokenSend(accessToken);
        if(!SUCCESS.getCode().equals(userResponse.code())) {
            throw new InvalidJwtException(userResponse.message());
        }

        tradeMessageService.purchaseSend(new PurchaseRequest(
                request.stockId(),
                userResponse.name(),
                request.stockOfAmount(),
                request.stockCount()
        ));

        return ResponseEntity.ok(new StockPurchaseResponse(
                SUCCESS.getCode(), SUCCESS.getMessage(), request.stockId(),
                request.stockCount() * request.stockOfAmount(), request.stockOfAmount(), request.stockCount()));
    }

}