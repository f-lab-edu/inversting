package com.flab.investing.stock.batch.step.krx;

import com.flab.investing.stock.batch.infrastructure.krx.dto.ApiResponse;
import com.flab.investing.stock.batch.infrastructure.krx.dto.Item;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockInformationProcessor implements ItemProcessor<ApiResponse, List<Item>> {

    @Override
    public List<Item> process(ApiResponse apiResponse) throws Exception {
        return apiResponse.response().body().items().item();
    }
}
