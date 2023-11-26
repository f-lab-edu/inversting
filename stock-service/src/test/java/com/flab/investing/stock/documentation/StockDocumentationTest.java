package com.flab.investing.stock.documentation;

import com.flab.investing.stock.application.StockService;
import com.flab.investing.stock.application.TradeMessageService;
import com.flab.investing.stock.application.TradeService;
import com.flab.investing.stock.application.UserService;
import com.flab.investing.stock.controller.response.ResponseCode;
import com.flab.investing.stock.controller.response.StockListInfo;
import com.flab.investing.stock.fixture.StockFixture;
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
    
    void stockList() {
        when(stockService.findAllPageable(any())).thenReturn(List.of(
                StockFixture.create("삼성전자", 1000),
                StockFixture.create("카카오", 2000),
                StockFixture.create("현대자동차", 3000),
                StockFixture.create("현대오토에버", 4000),
                StockFixture.create("LGCNS", 5000),
                StockFixture.create("삼성SDI", 6000),
                StockFixture.create("네이버", 7000),
                StockFixture.create("쌍용정보", 8000),
                StockFixture.create("KT", 9000)
        ));

        RestAssured
                .given().log().all()
                .filter(stockListDocumentationFilter("주식 리스트 조회"))
                .when()
                .get("/stocks?size=7&offset=0")
                .then().log().all()
                .extract();
    }


}
