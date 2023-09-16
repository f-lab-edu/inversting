package com.flab.investing.stock.controller;

import com.flab.investing.global.error.exception.InvalidJwtException;
import com.flab.investing.stock.application.StockService;
import com.flab.investing.stock.application.TradeMessageService;
import com.flab.investing.stock.application.TradeService;
import com.flab.investing.stock.application.UserService;
import com.flab.investing.stock.application.dto.TradeData;
import com.flab.investing.stock.common.DivisionStatus;
import com.flab.investing.stock.common.ExceptionCode;
import com.flab.investing.stock.controller.request.StockPurchaseRequest;
import com.flab.investing.stock.controller.response.ResultResponse;
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
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final UserService userService;
    private final TradeService tradeService;
    private final TradeMessageService tradeMessageService;

    @GetMapping
    public ResponseEntity<ResultResponse<StocksResponse>> stockList(@PageableDefault(size = 7) Pageable pageable) {
        List<StocksResponse> stockList = stockService.findAllPageable(pageable).stream()
                .map(stock -> new StocksResponse(stock.getId(), stock.getCode(), stock.getName(), stock.getPrice()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ResultResponse(
                ExceptionCode.SUCCESS.getCode(),
                ExceptionCode.SUCCESS.getDescription(),
                stockList
        ));
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<ResultResponse<StockInfoResponse>> stockInfo(@PathVariable Long stockId) {
        Stock stock = stockService.findByStockId(stockId);

        StockInfoResponse stockInfoResponse = new StockInfoResponse(
                stock.getId(),
                stock.getName(),
                stock.getCorporationCode(),
                stock.getStatus(),
                stock.getPrice(),
                stock.getStockHigh(),
                stock.getStockLower(),
                stock.getHighLimit(),
                stock.getLowerLimit()
        );

        return ResponseEntity.ok(new ResultResponse(
                ExceptionCode.SUCCESS.getCode(),
                ExceptionCode.SUCCESS.getDescription(),
                stockInfoResponse
        ));
    }

    @PostMapping("/purchases")
    public ResponseEntity<StockPurchaseResponse> purchase(@RequestHeader String accessToken,
                                                          @RequestBody StockPurchaseRequest request) {
        final UserResponse userResponse = userService.tokenSend(accessToken);
        if(!SUCCESS.getCode().equals(userResponse.code())) {
            throw new InvalidJwtException(userResponse.message());
        }

        Long tradeId = tradeService.saveAndGetId(new TradeData(
                request.stockId(),
                userResponse.userId(),
                request.stockCount(),
                request.stockOfAmount(),
                DivisionStatus.BUY
        ));

        tradeMessageService.purchaseSend(userResponse, request, tradeId);

        return ResponseEntity.ok(new StockPurchaseResponse(
                SUCCESS.getCode(), SUCCESS.getMessage(), request.stockId(),
                request.stockCount() * request.stockOfAmount(), request.stockOfAmount(), request.stockCount()));
    }

}
