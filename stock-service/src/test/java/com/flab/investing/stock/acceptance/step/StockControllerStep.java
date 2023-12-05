package com.flab.investing.stock.acceptance.step;

import com.flab.investing.stock.controller.request.StockPurchaseRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class StockControllerStep {

    public static ExtractableResponse<Response> stockListSearch() {
        return RestAssured
                .given().log().all()
                .queryParam("size", 7)
                .queryParam("offset", 0)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/stocks")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> stockInfoSearch(Long stockId) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("stockId", stockId)
                .when().get("/stocks/{stockId}")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> purchase(String accessToken,
                                                         StockPurchaseRequest request) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("accessToken", accessToken)
                .body(request)
                .when().get("/stocks/purchases")
                .then().log().all()
                .extract();
    }

}
