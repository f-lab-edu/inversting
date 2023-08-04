package com.flab.investing.stock.batch.step.krx;

import com.flab.investing.stock.batch.application.KrxService;
import com.flab.investing.stock.batch.infrastructure.krx.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockInformationReader implements ItemReader<ApiResponse> {

    private final KrxService krxService;

    @Override
    public ApiResponse read() {
        System.out.println("여기 통과하게 해주세요...");

        return krxService.send();
    }
}
