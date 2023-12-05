package com.flab.investing.stock.batch.application;

import com.flab.investing.stock.batch.domain.entity.DailyStock;
import com.flab.investing.stock.batch.domain.repository.DailyStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyStockService {

    private final DailyStockRepository dailyStockRepository;

    public void save(DailyStock dailyStocks) {
        dailyStockRepository.save(dailyStocks);
    }

}
