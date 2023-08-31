package com.flab.investing.stock.batch.job.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockInformationJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(final JobExecution jobExecution) {
        log.info("stockInformationJobListener 시작");  
    }

    @Override
    public void afterJob(final JobExecution jobExecution) {
        log.info("stockInformationJobListener 종료");
    }
}
