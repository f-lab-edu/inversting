package com.flab.investing.stock.batch.step.tasklet;

import com.flab.investing.stock.batch.application.KisService;
import com.flab.investing.stock.batch.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@StepScope
public class KisTokenTasklet implements Tasklet {

    private final KisService kisService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        Token token = kisService.getToken();

        jobExecutionContext.put("token", token);

        return RepeatStatus.FINISHED;
    }
}
