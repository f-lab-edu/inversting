package com.flab.investing.stock.acceptance;

import com.flab.investing.stock.acceptance.step.StockControllerStep;
import com.flab.investing.stock.common.ExceptionCode;
import com.flab.investing.stock.domain.Stock;
import com.flab.investing.stock.fixure.StockFixure;
import com.flab.investing.stock.repository.StockRepository;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StockListAcceptanceTest extends AcceptanceTest{

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    void setup() {
        super.setUp();

        List<Stock> stocks = List.of(
                StockFixure.create("삼성전자", 1000),
                StockFixure.create("카카오", 2000),
                StockFixure.create("현대자동차", 3000),
                StockFixure.create("현대오토에버", 4000),
                StockFixure.create("LGCNS", 5000),
                StockFixure.create("삼성SDI", 6000),
                StockFixure.create("네이버", 7000),
                StockFixure.create("쌍용정보", 8000),
                StockFixure.create("KT", 9000)
        );

        stockRepository.saveAll(stocks);
    }

    @DisplayName("주식 리스트를 보여준다.")
    @Test
    void stockList() {
        ExtractableResponse<Response> response = StockControllerStep.stockListSearch();

        assertThat(response.body().jsonPath().getString("code")).isEqualTo(ExceptionCode.SUCCESS.getCode());
        assertThat(response.body().jsonPath().getString("message")).isEqualTo(ExceptionCode.SUCCESS.getDescription());
        assertThat(response.body().jsonPath().getList("data")).hasSize(7);
    }

}
