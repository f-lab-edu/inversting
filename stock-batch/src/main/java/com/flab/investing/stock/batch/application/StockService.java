package com.flab.investing.stock.batch.application;

import com.flab.investing.stock.batch.infrastructure.krx.dto.Item;
import com.flab.investing.stock.batch.infrastructure.stock.StockDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockDao stockDao;

    public void save(final List<Item> items) {
        stockDao.save(items);
    }

}
