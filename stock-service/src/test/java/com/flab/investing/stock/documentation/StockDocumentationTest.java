package com.flab.investing.stock.documentation;

import com.flab.investing.stock.application.StockService;
import com.flab.investing.stock.application.TradeMessageService;
import com.flab.investing.stock.application.TradeService;
import com.flab.investing.stock.application.UserService;
import com.flab.investing.stock.fixure.StockFixure;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static com.flab.investing.stock.documentation.step.StockListStep.stockListDocumentationFilter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StockDocumentationTest extends Documentation {

    @MockBean
    private StockService stockService;

    @MockBean
    private UserService userService;

    @MockBean
    private TradeService tradeService;

    @MockBean
    private TradeMessageService tradeMessageService;

    @DisplayName("주식 리스트를 불러온다.")
    @Test
    void stockList() {
        when(stockService.findAllPageable(any())).thenReturn(List.of(
                StockFixure.create("삼성전자", 1000),
                StockFixure.create("카카오", 2000),
                StockFixure.create("현대자동차", 3000),
                StockFixure.create("현대오토에버", 4000),
                StockFixure.create("LGCNS", 5000),
                StockFixure.create("삼성SDI", 6000),
                StockFixure.create("네이버", 7000),
                StockFixure.create("쌍용정보", 8000),
                StockFixure.create("KT", 9000)
        ));

        RestAssured
                .given().log().all()
                .queryParam("size", 7)
                .queryParam("offset", 0)
                .filter(stockListDocumentationFilter("주식 리스트 조회"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/stocks")
                .then().log().all()
                .extract();
    }


}
