package com.flab.investing.stock.batch.step.krx;

import com.flab.investing.stock.batch.application.StockService;
import com.flab.investing.stock.batch.infrastructure.krx.dto.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockInformationWriter implements ItemWriter<List<Item>> {

    private final StockService stockService;

    @Override
    public void write(Chunk<? extends List<Item>> chunk) throws Exception {
        for (List<Item> itemList : chunk) {
            stockService.save(itemList);
        }
    }
}
