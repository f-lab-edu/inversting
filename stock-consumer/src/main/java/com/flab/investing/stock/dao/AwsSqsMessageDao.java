package com.flab.investing.stock.dao;

import com.flab.investing.stock.evnet.dto.TradeException;
import com.flab.investing.stock.infrastructure.AwsSqsMessageClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsSqsMessageDao {

    private final AwsSqsMessageClient awsSqsMessageClient;

    public void traceRollbackSend(TradeException tradeException) {
        log.info("거래가 오류가 나서 데이터를 전송합니다. ===> {} ", tradeException);
        awsSqsMessageClient.rollbackSend(tradeException);
    }

}
