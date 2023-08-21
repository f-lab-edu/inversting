package com.flab.investing.stock.batch.infrastructure.krx.dto;

import java.util.List;

public record ApiResponse(Response response) {

    public record Item(
            String basDt,
            String srtnCd,
            String isinCd,
            String mrktCtg,
            String itmsNm,
            String crno,
            String corpNm) {}

    public record Items(List<Item> item) { }

    public record Header(
            String resultCode,
            String resultMsg) {}

    public record Body(
            int numOfRows,
            int pageNo,
            int totalCount,
            Items items) {}

    public record Response(
            Header header,
            Body body) {}

}
