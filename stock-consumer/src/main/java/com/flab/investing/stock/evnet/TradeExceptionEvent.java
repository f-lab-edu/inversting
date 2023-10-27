package com.flab.investing.stock.evnet;

import com.flab.investing.stock.dao.RabbitMqDao;
import com.flab.investing.stock.evnet.dto.TradeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TradeExceptionEvent {

    private final RabbitMqDao rabbitMqDao;

    @EventListener
    public void process(TradeException tradeException) {
        log.info("주문하는 도중에 에러가 발생하였습니다. [{}]", tradeException.tradeId());
        rabbitMqDao.traceRollbackSend(tradeException);
    }

}
