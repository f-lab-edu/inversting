package com.flab.investing.stock.batch.step.tasklet;

import com.flab.investing.stock.batch.application.KrxService;
import com.flab.investing.stock.batch.application.StockService;
import com.flab.investing.stock.batch.domain.Stock;
import com.flab.investing.stock.batch.infrastructure.krx.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StockInformationTasklet implements Tasklet {

    private final KrxService krxService;
    private final StockService stockService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ApiResponse response = krxService.send();

        List<Stock> stocks = response.response().body().items().item()
                .stream()
                .map(item -> new Stock(null, item.isinCd(), item.srtnCd(), item.corpNm(), item.itmsNm(), item.crno()))
                .collect(Collectors.toList());

        stockService.save(stocks);
        return RepeatStatus.FINISHED;
    }
}
