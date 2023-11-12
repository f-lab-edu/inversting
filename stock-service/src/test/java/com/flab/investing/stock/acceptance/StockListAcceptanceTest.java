package com.flab.investing.stock.acceptance;

import com.flab.investing.stock.acceptance.step.StockControllerStep;
import com.flab.investing.stock.controller.response.ResponseCode;
import com.flab.investing.stock.domain.entity.Stock;
import com.flab.investing.stock.fixture.StockFixture;
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
                StockFixture.create("삼성전자", 1000),
                StockFixture.create("카카오", 2000),
                StockFixture.create("현대자동차", 3000),
                StockFixture.create("현대오토에버", 4000),
                StockFixture.create("LGCNS", 5000),
                StockFixture.create("삼성SDI", 6000),
                StockFixture.create("네이버", 7000),
                StockFixture.create("쌍용정보", 8000),
                StockFixture.create("KT", 9000)
        );

        stockRepository.saveAll(stocks);
    }

    @DisplayName("주식 리스트를 보여준다.")
    @Test
    void stockList() {
        ExtractableResponse<Response> response = StockControllerStep.stockListSearch();

        assertThat(response.body().jsonPath().getString("code")).isEqualTo(ResponseCode.SUCCESS.getCode());
        assertThat(response.body().jsonPath().getString("message")).isEqualTo(ResponseCode.SUCCESS.getMessage());
        assertThat(response.body().jsonPath().getList("data")).hasSize(7);
    }

}
