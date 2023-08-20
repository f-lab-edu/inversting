package com.flab.investing.stock.controller;

import com.flab.investing.global.error.exception.InvalidJwtException;
import com.flab.investing.stock.application.StockService;
import com.flab.investing.stock.application.TradeMessageService;
import com.flab.investing.stock.application.UserService;
import com.flab.investing.stock.application.dto.PurchaseRequest;
import com.flab.investing.stock.controller.request.StockPurchaseRequest;
import com.flab.investing.stock.controller.response.StockInfoResponse;
import com.flab.investing.stock.controller.response.StockPurchaseResponse;
import com.flab.investing.stock.controller.response.StocksResponse;
import com.flab.investing.stock.infrastructure.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final UserService userService;
    private final TradeMessageService tradeMessageService;

    @GetMapping("/list")
    public ResponseEntity<List<StocksResponse>> stockList(@PageableDefault(size = 7) Pageable pageable) {
        return ResponseEntity.ok(stockService.findAllPageable(pageable));
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockInfoResponse> stockInfo(@PathVariable Long stockId) {
        return ResponseEntity.ok(stockService.findByStockId(stockId));
    }

    @PostMapping("/purchase")
    public ResponseEntity<StockPurchaseResponse> purchase(@RequestHeader String accessToken,
                                                          @RequestBody StockPurchaseRequest request) {
        UserResponse userResponse = userService.tokenSend(accessToken);
        if(!"0000".equals(userResponse.code())) {
            throw new InvalidJwtException(userResponse.message());
        }

        tradeMessageService.purchaseSend(new PurchaseRequest(
                request.stockId(),
                userResponse.name(),
                request.stockOfAmount(),
                request.stockCount()
        ));

        return ResponseEntity.ok(new StockPurchaseResponse(
                "0000", "정상 체결되었습니다.", request.stockId(),
                request.stockCount() * request.stockOfAmount(), request.stockOfAmount(), request.stockCount()));
    }

}
