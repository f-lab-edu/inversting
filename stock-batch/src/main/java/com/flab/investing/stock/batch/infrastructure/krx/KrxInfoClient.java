package com.flab.investing.stock.batch.infrastructure.krx;

import com.flab.investing.stock.batch.infrastructure.krx.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "krxInfo", url = "${krx.url}")
public interface KrxInfoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getItemInfo")
    ApiResponse getItemInfo(@RequestParam("serviceKey") String serviceKey,
                            @RequestParam("numOfRows") int numOfRows,
                            @RequestParam("pageNo") int pageNo,
                            @RequestParam("resultType") String resultType);

}
