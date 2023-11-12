package com.flab.investing.stock.acceptance;

import com.flab.investing.global.error.exception.constant.ExceptionMessage;
import com.flab.investing.stock.acceptance.step.StockControllerStep;
import com.flab.investing.stock.domain.entity.Stock;
import com.flab.investing.stock.fixture.StockFixture;
import com.flab.investing.stock.repository.StockRepository;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class StockInfoAcceptanceTest extends AcceptanceTest{

    @Autowired
    private StockRepository stockRepository;

    private Stock samsung;

    @BeforeEach
    void setup() {
        super.setUp();

        this.samsung = stockRepository.save(StockFixture.create("삼성전자", 1000));
    }

    @DisplayName("주식 정보를 조회 한다.")
    @Test
    void stockInfo() {
        ResponseBodyExtractionOptions response = StockControllerStep.stockInfoSearch(samsung.getId()).body();

        assertThat(response.jsonPath().getString("data.name")).isEqualTo("삼성전자");
    }

    @DisplayName("없는 주식 정보조회시, 에러를 반환한다.")
    @Test
    void notFoundStockInfoException() {
        ResponseBodyExtractionOptions response = StockControllerStep.stockInfoSearch(100L).body();

        assertThat(response.jsonPath().getString("code")).isEqualTo("9999");
        assertThat(response.jsonPath().getString("message")).isEqualTo(ExceptionMessage.NOT_FOUND_STOCK.getMessage());
    }

}
